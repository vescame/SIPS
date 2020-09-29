package edu.fatec.sips.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Curso;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ArquivoCandidatoController {
	final ArquivoDocumentoCandidatoController documentos;
	final ArquivoCursoController cursos;
	private final String ARQUIVO = "ArquivoCandidato.txt";
	private final String SEPARADOR = ";";
	
	public ArquivoCandidatoController() {
		this.cursos = new ArquivoCursoController();
		this.documentos = new ArquivoDocumentoCandidatoController();
	}
	
	public int ultimoId() throws IOException {
		String linha = new String();
		String linhaAnterior = new String();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			linhaAnterior = linha;
		}
		
		Candidato temp = quebrarAtributos(linhaAnterior);
		
		br.close();
		
		return temp.getId() + 1;
	}
	
	
	public Candidato buscarPorId(final int id) throws IOException {
		Candidato encontrado = null;
		String linha = new String();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while (encontrado == null && (linha = br.readLine()) != null) {
			Candidato tempCandidato = quebrarAtributos(linha);
			if (tempCandidato.getId() == id) {
				encontrado = tempCandidato;
			}
		}
		
		br.close();
		
		return encontrado;
	}
	
	public ListaLigadaSimples<Candidato> listarCandidatos() throws IOException {
		String linha = new String();
		ListaLigadaSimples<Candidato> candidatos = new ListaLigadaSimples<Candidato>();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			Candidato tempCandidato = quebrarAtributos(linha);
			candidatos.adicionar(tempCandidato);
		}
		
		br.close();
		
		return candidatos;
	}
	
	public void atualizarCandidato(final Candidato candidato) throws IOException {
		throw new NotImplementedException();
	}
	
	public Candidato removerCandidato(final Candidato candidato) throws IOException {
		throw new NotImplementedException();
	}

	private Candidato quebrarAtributos(String linha) {
		Candidato candidato = new Candidato();
		try {
			String[] atribs = linha.split(SEPARADOR);
			candidato.setId(Integer.valueOf(atribs[0]));
			candidato.setNome(atribs[1]);
			candidato.setSobrenome(atribs[2]);
			candidato.setDataNascimento(LocalDate.parse(atribs[3]));
			int idCurso = Integer.valueOf(atribs[4]);
			Curso curso = cursos.buscarPorId(idCurso);
			candidato.setCurso(curso);
			candidato.setAprovado(Boolean.valueOf(atribs[5]));
			candidato.setDocumentos(documentos.listarDocumentos(candidato.getId()));
		} catch (Exception e) {
			System.out.println("falha ao obter atributo");
		}
		
		return candidato;
	}

	public void gravarCandidato(final Candidato candidato) throws IOException {
		FileWriter fw = new FileWriter(ARQUIVO, true);
		fw.write("\n" + concatenarCandidato(candidato));
		fw.close();
	}
	
	private String concatenarCandidato(final Candidato candidato) {
		StringBuilder linha = new StringBuilder().append(candidato.getId()).append(SEPARADOR)
				.append(candidato.getNome()).append(SEPARADOR)
				.append(candidato.getSobrenome()).append(SEPARADOR)
				.append(candidato.getDataNascimento().toString()).append(SEPARADOR)
				.append(candidato.getCurso().getId()).append(SEPARADOR)
				.append(candidato.isAprovado());
		
		return linha.toString();
	}
}
