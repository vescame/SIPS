package edu.fatec.sips.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Curso;
import edu.fatec.sips.model.Edital;

public class ResultadoPreliminar {

	private final ArquivoCandidatoController bdCandidato;
	private final ArquivoEditalController bdEdital;
	private final String ARQUIVO = "ResultadoPreliminar.txt";
	private final String SEPARADOR = ";";
	private ListaLigadaSimples<Candidato> listaDeCandidatos;
	private ListaLigadaSimples<Edital> listaDeEditais;

	public ResultadoPreliminar() {
		this.bdCandidato = new ArquivoCandidatoController();
		this.bdEdital = new ArquivoEditalController();
	}

	public ListaLigadaSimples<Candidato> listarCandidatos() {
		try {
			return this.bdCandidato.listarCandidatos();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ListaLigadaSimples<Edital> listarEditais() {
		try {
			return this.bdEdital.listarEditais();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ListaLigadaSimples<Candidato> carregarNovosDadosDeCandidadosListados() {
		listaDeCandidatos = listarCandidatos();
		for (int i = 0; i < listaDeCandidatos.getTamanho(); i++) {
			inserirNotaDeCandidato(listaDeCandidatos, i);
			inserirCriterioDeCandidato(listaDeCandidatos, i);
		}
		return listaDeCandidatos;
	}

	public ListaLigadaSimples<Edital> carregarDadosDeEditaisListados() {
		return listaDeEditais = listarEditais();
	}

	public void inserirNotaDeCandidato(ListaLigadaSimples<Candidato> lista, int i) {
		lista.espiar(i).setNota(sortearNumerosEntreIntervalos(10, 0));
	}

	public void filtrarCandidatosPorCursoConformeEdital(ListaLigadaSimples<Candidato> candidato,
			ListaLigadaSimples<Edital> edital) {
		for (int i = 0; i < edital.getTamanho(); i++) {
			for (int j = 0; j < candidato.getTamanho(); j++) {
				if (edital.espiar(i).getCurso().contains(candidato.espiar(j).getCurso().getSigla())) {
					pegarCandidatosClassificados(candidato);
				}
			}
		}

	}

	public ListaLigadaSimples<Candidato> pegarCandidatosClassificados(
			ListaLigadaSimples<Candidato> listaDeCandidatosClassificados) {
		return listaDeCandidatosClassificados;
	}

	public int sortearNumerosEntreIntervalos(int max, int min) {
		Random numero = new Random();
		return numero.nextInt((max - min) + 1) + min;
	}

	public void inserirCriterioDeCandidato(ListaLigadaSimples<Candidato> lista, int i) {
		lista.espiar(i).setCriterio(sortearNumerosEntreIntervalos(4, 1));
	}

	public void gravarResultadoPreliminar() throws IOException {
		listaDeCandidatos = pegarCandidatosClassificados(carregarNovosDadosDeCandidadosListados());
		listaDeEditais = carregarDadosDeEditaisListados();
		filtrarCandidatosPorCursoConformeEdital(listaDeCandidatos, listaDeEditais);
		FileWriter fw = new FileWriter(ARQUIVO, true);
//		fw.write(retornarStringDeCandidatosQualificados(listaDeCandidatos, listaDeEditais));
		fw.close();
	}

	public int definirCandidatosComMaiorNota(ListaLigadaSimples<Candidato> lista) {
		int maiorNota = 0;
		for (int i = 0; i < lista.getTamanho(); i++) {
			for (int j = 0; j < i; j++) {
				if (lista.espiar(j).getNota() > lista.espiar(i).getNota()) {
					if (maiorNota < lista.espiar(j).getNota()) {
						maiorNota = lista.espiar(j).getNota();
					}
				}
			}
		}
		return maiorNota;
	}

	private String retornarStringDeCandidatosQualificados(final ListaLigadaSimples<Candidato> candidato,
			final ListaLigadaSimples<Edital> edital, final ListaLigadaSimples<Curso> curso) {
		StringBuilder linha = new StringBuilder();
		for (int i = 0; i < candidato.getTamanho(); i++) {
			if (definirCandidatosComMaiorNota(pegarCandidatosClassificados(candidato)) == candidato.espiar(i).getNota()) {
				linha.append(candidato.espiar(i).getId()).append(SEPARADOR).append(candidato.espiar(i).getNome())
						.append(SEPARADOR).append(candidato.espiar(i).getSobrenome()).append(SEPARADOR)
						.append(candidato.espiar(i).getDataNascimento().toString()).append(SEPARADOR)
						.append(candidato.espiar(i).getCurso().getId()).append(SEPARADOR)
						.append(candidato.espiar(i).isAprovado()).append(SEPARADOR)
						.append(candidato.espiar(i).getNota()).append(SEPARADOR)
						.append(candidato.espiar(i).getCriterio()).append("\n");
			}

		}
		System.out.println(definirCandidatosComMaiorNota(candidato));
		return linha.toString();
	}

	public static void main(String[] args) throws IOException {
		ResultadoPreliminar resultadoPreliminar = new ResultadoPreliminar();
		resultadoPreliminar.gravarResultadoPreliminar();
	}

}
