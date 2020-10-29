package edu.fatec.sips.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Curso;

public class ArquivoCandidatoController {
	final ArquivoDocumentoCandidatoController documentos;
	final ArquivoCursoController cursos;
	private final String ARQUIVO = "ArquivoCandidato.txt";
	private final String SEPARADOR = ";";
	private final SimpleDateFormat sdf;
	
	public ArquivoCandidatoController() {
		this.cursos = new ArquivoCursoController();
		this.documentos = new ArquivoDocumentoCandidatoController();
		this.sdf = new SimpleDateFormat("dd/MM/yyyy");
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
	
	public ListaLigadaSimples<Candidato> listarCandidatos() throws IOException {
		String linha = new String();
		ListaLigadaSimples<Candidato> candidatos = new ListaLigadaSimples<Candidato>();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			Candidato tempCandidato = quebrarAtributos(linha);
			candidatos.inserirPrimeiro(tempCandidato);
		}
		
		br.close();
		
		return candidatos;
	}
	
	public void atualizarCandidato(final Candidato candidato) throws Exception {
		String linhaAtual = new String();
		
		File arquivoEntrada = new File(this.ARQUIVO);
		File arquivoTemporario = new File("tmp." + this.ARQUIVO);

		BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada));
		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemporario));

		while ((linhaAtual = br.readLine()) != null) {
			Candidato candidatoAtual = quebrarAtributos(linhaAtual);
			
			if (candidatoAtual.getId() == candidato.getId()) {
				candidatoAtual = candidato;
			}
			
			bw.write(concatenarCandidato(candidatoAtual) + "\n");
		}
		
		bw.close();
		br.close();
		arquivoEntrada.delete();
		arquivoTemporario.renameTo(new File(this.ARQUIVO));
	}
	
	public Candidato removerCandidato(final Candidato candidato) throws Exception {
		throw new Exception();
	}

	private Candidato quebrarAtributos(String linha) {
		Candidato candidato = new Candidato();
		try {
			String[] atribs = linha.split(SEPARADOR);
			candidato.setId(Integer.valueOf(atribs[0]));
			candidato.setNome(atribs[1]);
			candidato.setSobrenome(atribs[2]);
			candidato.setDataNascimento(this.sdf.parse(atribs[3]));
			int idCurso = Integer.valueOf(atribs[4]);
			Curso curso = cursos.buscarPorId(idCurso);
			candidato.setCurso(curso);
			candidato.setAprovado(Boolean.valueOf(atribs[5]));
			candidato.setDocumentos(documentos.listarDocumentos(candidato.getId()));
		} catch (Exception e) {
			System.out.println("falha ao obter atributo candidato");
		}
		
		return candidato;
	}

	public void gravarCandidato(final Candidato candidato) throws IOException {
		FileWriter fw = new FileWriter(ARQUIVO, true);
		fw.write(concatenarCandidato(candidato) + "\n");
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
