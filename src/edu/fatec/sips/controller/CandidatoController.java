package edu.fatec.sips.controller;

import java.io.IOException;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.data_structure.No;
import edu.fatec.sips.data_structure.search.BuscaBinariaCandidato;
import edu.fatec.sips.data_structure.sorting.MergeSortCandidatos;
import edu.fatec.sips.data_structure.sorting.ShellSortCandidatos;
import edu.fatec.sips.model.Candidato;

public class CandidatoController {
	private final ArquivoCandidatoController bdCandidato;
	private ListaLigadaSimples<Candidato> candidatos;

	public CandidatoController() {
		this.bdCandidato = new ArquivoCandidatoController();
	}

	public Candidato getPorId(int id) {
		Candidato candidato = null;
		
		// para dados em ordem decrescente oferece o melhor custo benefício
		ShellSortCandidatos shellSort = new ShellSortCandidatos();

		try {
			if (candidatos == null) {
				candidatos = bdCandidato.listarCandidatos();
				shellSort.shellSort(candidatos.primeiro, candidatos.getTamanho());
			}

			No<Candidato> p = candidatos.primeiro;
			candidato = new BuscaBinariaCandidato().buscaBinaria(p, id).getElemento();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (candidato == null) {
				System.out.println("candidato de id " + id + " nao encontrado");
			}
		}

		return candidato;
	}

	public ListaLigadaSimples<Candidato> listarCandidatosOrdenados() {
		ListaLigadaSimples<Candidato> candidatos = null;
		try {
			candidatos = this.bdCandidato.listarCandidatos();
			
			MergeSortCandidatos mergeSortCandidatos = new MergeSortCandidatos();
			mergeSortCandidatos.mergeSort(candidatos.primeiro);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return candidatos;
	}

	public ListaLigadaSimples<Candidato> listarCandidatos() {
		try {
			return this.bdCandidato.listarCandidatos();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
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
