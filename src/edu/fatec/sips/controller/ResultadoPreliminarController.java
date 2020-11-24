package edu.fatec.sips.controller;

import java.io.IOException;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.file_controller.ArquivoResultadoPreliminarController;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.ResultadoPreliminar;

public class ResultadoPreliminarController {
	private ListaLigadaSimples<ResultadoPreliminar> resultados;
	private ArquivoResultadoPreliminarController bdResultadoPreliminar;

	public ResultadoPreliminarController() {
		this.bdResultadoPreliminar = new ArquivoResultadoPreliminarController();
		this.resultados = new ListaLigadaSimples<ResultadoPreliminar>();
	}

	public ResultadoPreliminar getPorId(int id) {
		ResultadoPreliminar encontrado = null;

		try {
			encontrado = this.bdResultadoPreliminar.buscarPorId(id);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (encontrado == null) {
				System.out.println("resultado preliminar, de id " + id + " nao encontrado");
			}
		}

		return encontrado;
	}

	public ListaLigadaSimples<ResultadoPreliminar> listarResultadoPreliminar() {
		try {
			return this.bdResultadoPreliminar.listarResultadoPreliminar();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void atualizar(final ResultadoPreliminar resultadoPreliminar) {
		try {
			this.bdResultadoPreliminar.atualizarResultadoPreliminar(resultadoPreliminar);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ResultadoPreliminar remover(final ResultadoPreliminar resultadoPreliminar) {
		ResultadoPreliminar removido = null;

		try {
			removido = this.bdResultadoPreliminar.removerPreliminar(resultadoPreliminar);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (removido == null) {
				System.out.println("resultado preliminar, de id " + resultadoPreliminar.getId() + " nao existe");
			}
		}

		return removido;
	}

	public void definirResultadoPreliminar() {
		CandidatoController candidatoController = new CandidatoController();
		ListaLigadaSimples<Candidato> listaDeCandidato = candidatoController.listarCandidatos();
		long start = System.nanoTime();
		for (int i = 0; i < listaDeCandidato.getTamanho(); i++) {
			final Candidato c = listaDeCandidato.espiar(i);
			if (c.getNota() >= 7) {
				if (c.getEdital() != null) {
					ResultadoPreliminar resultadoPreliminar = new ResultadoPreliminar();
					resultadoPreliminar.setCandidato(c);
					this.resultados.adicionar(resultadoPreliminar);
				}
			}
		}
		try {
			final int tamanho = this.resultados.getTamanho();
			for (int i = 0; i < tamanho; ++i) {
				final ResultadoPreliminar resultadoPreliminar = resultados.espiar(i);
				this.bdResultadoPreliminar.gravarResultadoPreliminar(resultadoPreliminar);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.nanoTime();
		long elapsedTime = end - start;
		System.out.println(elapsedTime / 1000000000);
	}

}
