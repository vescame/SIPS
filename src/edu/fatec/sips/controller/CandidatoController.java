package edu.fatec.sips.controller;

import java.io.IOException;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.data_structure.No;
import edu.fatec.sips.data_structure.search.BuscaBinariaCandidato;
import edu.fatec.sips.data_structure.sorting.MergeSortCandidatos;
import edu.fatec.sips.data_structure.sorting.ShellSortCandidatos;
import edu.fatec.sips.file_controller.ArquivoCandidatoController;
import edu.fatec.sips.model.Candidato;

public class CandidatoController {
	private final ArquivoCandidatoController bdCandidato;
	private ListaLigadaSimples<Candidato> candidatos;

	public CandidatoController() {
		this.bdCandidato = new ArquivoCandidatoController();
		try {
			this.candidatos = bdCandidato.listarCandidatos();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Candidato getPorId(int id) {
		Candidato candidato = null;

		ShellSortCandidatos shellSort = new ShellSortCandidatos();

		try {
			if (candidatos.espiar(0).getId() != 1) {
				shellSort.shellSort(candidatos.primeiro, candidatos.getTamanho());
			}

			No<Candidato> p = candidatos.primeiro;
			candidato = new BuscaBinariaCandidato().buscaBinaria(p, id).getElemento();
		} finally {
			if (candidato == null) {
				System.out.println("candidato de id " + id + " nao encontrado");
			}
		}

		return candidato;
	}

	public ListaLigadaSimples<Candidato> listarCandidatosOrdenados() {
		ListaLigadaSimples<Candidato> candidatosOrdenados = new ListaLigadaSimples<>();
		final int tamanho = this.candidatos.getTamanho();
		
		Candidato[] vetorCandidatos = new Candidato[tamanho];
		
		MergeSortCandidatos mergeSortCandidatos = new MergeSortCandidatos();
		
		for (int i = 0; i < tamanho; ++i) {
			vetorCandidatos[i] = this.candidatos.espiar(i);
		}
		
		vetorCandidatos = mergeSortCandidatos.sort(vetorCandidatos);
		
		for (int i = 0; i < tamanho; ++i) {
			candidatosOrdenados.adicionar(vetorCandidatos[i]);
		}
		
		return candidatosOrdenados;
	}

	public ListaLigadaSimples<Candidato> listarCandidatos() {
		ListaLigadaSimples<Candidato> candidatos = null;

		try {
			candidatos = this.bdCandidato.listarCandidatos();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return candidatos;
	}

	public void salvar(final Candidato candidato) {
		try {
			this.bdCandidato.gravarCandidato(candidato);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(final Candidato candidato) {
		try {
			this.bdCandidato.atualizarCandidato(candidato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void atualizar(final ListaLigadaSimples<Candidato> candidatos) {
		try {
			this.bdCandidato.atualizarCandidatos(candidatos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Candidato remover(final Candidato candidato) {
		Candidato removido = null;

		try {
			removido = this.bdCandidato.removerCandidato(candidato);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (removido == null) {
				System.out.println("curso de id " + candidato.getId() + " nao existe");
			}
		}

		return removido;
	}
}
