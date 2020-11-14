package edu.fatec.sips.controller;

import java.io.IOException;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Edital;

public class EditalController {
	private final ArquivoEditalController bdEdital;

	public EditalController() {
		this.bdEdital = new ArquivoEditalController();
	}

	public Edital getPorId(int id) {
		Edital encontrado = null;

		try {
			encontrado = this.bdEdital.buscarPorId(id);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (encontrado == null) {
				System.out.println("edital de id " + id + " nao encontrado");
			}
		}

		return encontrado;
	}

	public ListaLigadaSimples<Edital> listarEditais() {
		try {
			return this.bdEdital.listarEditais();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void salvar(final Edital edital) {
		try {
			this.bdEdital.gravarEdital(edital);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(final Edital edital) throws Exception {
		try {
			this.bdEdital.atualizarEdital(edital);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Edital remover(final Edital edital) throws Exception {
		Edital removido = null;

		try {
			removido = this.bdEdital.removerEdital(edital);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (removido == null) {
				System.out.println("edital de id " + edital.getId() + " nao existe");
			}
		}

		return removido;
	}
}
