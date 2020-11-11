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
	// para dados em ordem decrescente oferece o melhor custo benefício
	ShellSortCandidatos shellSort;

	public CandidatoController() {
		this.bdCandidato = new ArquivoCandidatoController();
		this.shellSort = new ShellSortCandidatos();;
	}

	public Candidato getPorId(int id) {
		Candidato candidato = null;

		try {
			ListaLigadaSimples<Candidato> candidatos = bdCandidato.listarCandidatos();
			
			if (this.shellSort.getTamanho() == 0) {
				for (int i = 0; i < candidatos.getTamanho(); ++i) {
					shellSort.adicionar(candidatos.espiar(i));
				}
				
				shellSort.shellSort();
			}			
			No<Candidato> p = shellSort.primeiro;
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

	public MergeSortCandidatos listarCandidatosOrdenados() {
		try {
			MergeSortCandidatos mergeSortCandidatos = new MergeSortCandidatos();
			ListaLigadaSimples<Candidato> candidatos = this.bdCandidato.listarCandidatos();

			for (int i = 0; i < candidatos.getTamanho(); ++i) {
				mergeSortCandidatos.inserirPrimeiro(candidatos.espiar(i));
			}

			mergeSortCandidatos.mergeSort();

			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
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
