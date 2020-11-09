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
	private final ArquivoCursoController bdCurso;
	private final String ARQUIVO = "ResultadoPreliminar.txt";
	private final String SEPARADOR = ";";
	private ListaLigadaSimples<Candidato> listaDeCandidatos;
	private ListaLigadaSimples<Curso> listaDeCursos;
	private ListaLigadaSimples<Edital> listaDeEditais;

	public ResultadoPreliminar() {
		this.bdCandidato = new ArquivoCandidatoController();
		this.bdCurso = new ArquivoCursoController();
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

	public ListaLigadaSimples<Curso> listarCursos() {
		try {
			return this.bdCurso.listarCursos();

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

	public ListaLigadaSimples<Curso> carregarDadosDeCursos() {
		return listaDeCursos = listarCursos();
	}

	public void inserirNotaDeCandidato(ListaLigadaSimples<Candidato> lista, int i) {
		lista.espiar(i).setNota(sortearNumerosEntreIntervalos(10, 0));
	}

	public void filtrarCandidatosPorCursoConformeEdital(ListaLigadaSimples<Candidato> candidato,
			ListaLigadaSimples<Curso> curso) {
		int n = 1;
		for (int i = 0; i < curso.getTamanho(); i++) {
			for (int j = 0; j < candidato.getTamanho(); j++) {
				if (curso.espiar(i).getId() == n && candidato.espiar(j).getCurso().getId() == n) {
					System.out.println(candidato.espiar(j).getNome() + " " + candidato.espiar(j).getSobrenome() + " - "
							+ curso.espiar(i).getSigla());
					System.out.println(n);
				}
			}
			n++;
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
//		filtrarCandidatosPorCursoConformeEdital(listaDeCandidatos, listaDeCursos);
		FileWriter fw = new FileWriter(ARQUIVO, true);
		fw.write(retornarStringDeCandidatosQualificados(listaDeCandidatos, listaDeCursos));
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
			final ListaLigadaSimples<Curso> curso) {
		StringBuilder linha = new StringBuilder();
//		filtrarCandidatosPorCursoConformeEdital(candidato, curso);
		for (int i = 0; i < candidato.getTamanho(); i++) {
			for (int j = 0; j < curso.getTamanho(); j++) {
				if (definirCandidatosComMaiorNota(candidato) == candidato.espiar(i).getNota()
						&& candidato.espiar(i).getCurso().getSigla().contains(curso.espiar(j).getSigla())) {
					linha.append(candidato.espiar(i).getId()).append(SEPARADOR).append(candidato.espiar(i).getNome())
							.append(SEPARADOR).append(candidato.espiar(i).getSobrenome()).append(SEPARADOR)
							.append(candidato.espiar(i).getDataNascimento().toString()).append(SEPARADOR)
							.append(candidato.espiar(i).getCurso().getId()).append(SEPARADOR)
							.append(candidato.espiar(i).isAprovado()).append(SEPARADOR)
							.append(candidato.espiar(i).getNota()).append(SEPARADOR)
							.append(candidato.espiar(i).getCriterio()).append("\n");
				}
			}

		}
		return linha.toString();
	}

	public static void main(String[] args) throws IOException {
		ResultadoPreliminar resultadoPreliminar = new ResultadoPreliminar();
		ListaLigadaSimples<Candidato> candidato = new ListaLigadaSimples<Candidato>();
		ListaLigadaSimples<Curso> curso = new ListaLigadaSimples<Curso>();
		candidato = resultadoPreliminar.carregarNovosDadosDeCandidadosListados();
		curso = resultadoPreliminar.carregarDadosDeCursos();
		resultadoPreliminar.filtrarCandidatosPorCursoConformeEdital(candidato, curso);
	}

}
