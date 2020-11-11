package edu.fatec.sips.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.enums.Etapa;
import edu.fatec.sips.model.Recurso;

public class ArquivoRecursoController {
	private final String ARQUIVO = "ArquivoRecurso.txt";
	private final String SEPARADOR = ";";
	
	public int ultimoId() throws IOException {
		String linha = new String();
		String linhaAnterior = new String();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			linhaAnterior = linha;
		}
		
		Recurso temp = quebrarAtributos(linhaAnterior);
		
		br.close();
		
		return temp.getId() + 1;
	}
	
	public Recurso buscarPorId(final int id) throws IOException {
		Recurso encontrado = null;
		String linha = new String();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while (encontrado == null && (linha = br.readLine()) != null) {
			Recurso temp = quebrarAtributos(linha);
			if (temp.getId() == id) {
				encontrado = temp;
			}
		}
		
		br.close();
		
		return encontrado;
	}
	
	public ListaLigadaSimples<Recurso> buscarPorIdCandidato(final int idCandidato) throws IOException {
		ListaLigadaSimples<Recurso> encontrados = new ListaLigadaSimples<Recurso>();
		String linha = new String();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			Recurso temp = quebrarAtributos(linha);
			if (temp.getIdCandidato() == idCandidato) {
				encontrados.adicionar(temp);
			}
		}
		
		br.close();
		
		return encontrados;
	}
	
	public ListaLigadaSimples<Recurso> listarRecursos() throws IOException {
		String linha = new String();
		ListaLigadaSimples<Recurso> cursos = new ListaLigadaSimples<Recurso>();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			Recurso temp = quebrarAtributos(linha);
			if (!temp.isFechado()) {
				cursos.adicionar(temp);
			}
		}
		
		br.close();
		
		return cursos;
	}

	public void gravarRecurso(final Recurso recurso) throws IOException {
		recurso.setId(ultimoId());
		FileWriter fw = new FileWriter(ARQUIVO, true);
		fw.write(concatenarRecurso(recurso) + "\n");
		fw.close();
	}
	
	public void atualizarRecurso(final Recurso recursoAtualizado) throws IOException {
		String linhaAtual = new String();
		
		File arquivoEntrada = new File(this.ARQUIVO);
		File arquivoTemporario = new File("tmp." + this.ARQUIVO);

		BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada));
		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemporario));

		while ((linhaAtual = br.readLine()) != null) {
			Recurso recursoAtual = quebrarAtributos(linhaAtual);
			
			if (recursoAtual.getId() == recursoAtualizado.getId()) {
				recursoAtual = recursoAtualizado;
			}
			
			bw.write(concatenarRecurso(recursoAtual) + "\n");
		}
		
		bw.close();
		br.close();
		arquivoEntrada.delete();
		arquivoTemporario.renameTo(new File(this.ARQUIVO));
	}

	private Recurso quebrarAtributos(String linha) {
		Recurso recurso = new Recurso();
		try {
			String[] atribs = linha.split(SEPARADOR);
			recurso.setId(Integer.valueOf(atribs[0]));
			recurso.setIdCandidato(Integer.parseInt(atribs[1]));
			recurso.setEtapaRecurso(transformaEtapa(atribs[2]));
			recurso.setDescricao(atribs[3]);
			recurso.setAprovado(Boolean.valueOf(atribs[4]));
			recurso.setFechado(Boolean.valueOf(atribs[5]));
		} catch (Exception e) {
			System.out.println("falha ao obter atributo etapa");
		}
		
		return recurso;
	}
	
	private String concatenarRecurso(final Recurso recurso) {
		StringBuilder linha = new StringBuilder()
				.append(recurso.getId()).append(SEPARADOR)
				.append(recurso.getIdCandidato()).append(SEPARADOR)
				.append(recurso.getEtapaRecurso()).append(SEPARADOR)
				.append(recurso.getDescricao()).append(SEPARADOR)
				.append(recurso.isAprovado()).append(SEPARADOR)
				.append(recurso.isFechado());
		
		return linha.toString();
	}
	
	private Etapa transformaEtapa(String etapa) {
		Etapa transformada = null;
		
		for (Etapa e : Etapa.values()) {
			if (e.toString().equals(etapa)) {
				transformada = e;
			}
		}
		
		return transformada;
	}
}
