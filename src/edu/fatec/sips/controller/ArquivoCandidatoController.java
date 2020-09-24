package edu.fatec.sips.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;

public class ArquivoCandidatoController {
	private final String ARQUIVO = "ArquivoCandidato.txt";
	private final String SEPARADOR = ";";
	
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

	private Candidato quebrarAtributos(String linha) {
		Candidato candidato = new Candidato();
		try {
			String[] atribs = linha.split(SEPARADOR);
			candidato.setId(Integer.valueOf(atribs[0]));
			candidato.setNome(atribs[1]);
			candidato.setSobrenome(atribs[2]);
			candidato.setDataNascimento(LocalDate.parse(atribs[3]));
			// int idCurso = Integer.valueOf(atribs[4]);
			// Curso curso = ArquivoCursoController.getPorId(idCurso)
			// candidato.setCurso(curso);
			candidato.setAprovado(Boolean.valueOf(atribs[5]));
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
