package edu.fatec.sips.controller;

import java.io.IOException;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.ResultadoPreliminar;

public class ResultadoPreliminarController {

	private ArquivoResultadoPreliminarController bdResultadoPreliminar;

	public ResultadoPreliminarController() {
		this.bdResultadoPreliminar = new ArquivoResultadoPreliminarController();
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

	public void salvar(final ResultadoPreliminar resultadoPreliminar) {
		try {
			this.bdResultadoPreliminar.gravarResultadoPreliminar(resultadoPreliminar);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public void definirResultadoPreliminar(){
		CandidatoController candidatoController = new CandidatoController();
		ListaLigadaSimples<Candidato> listaDeCandidato = candidatoController.listarCandidatos();
		ResultadoPreliminar resultadoPreliminar = new ResultadoPreliminar();
		for (int i = 0; i < listaDeCandidato.getTamanho(); i++) {
			if (listaDeCandidato.espiar(i).getNota() >= 7) {
				resultadoPreliminar.setCandidato(listaDeCandidato.espiar(i));
				salvar(resultadoPreliminar);
			}
		}
	}

}
