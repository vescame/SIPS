package edu.fatec.sips.controller;

import java.io.IOException;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;

public class CandidatoController {
	private final ArquivoCandidatoController bdCandidato;
	
	public CandidatoController() {
		this.bdCandidato = new ArquivoCandidatoController();
	}
	
	public Candidato getPorId(int id) {
		Candidato candidato = null;
		
		try {
			candidato = this.bdCandidato.buscarPorId(id);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (candidato == null) {
				System.out.println("candidato de id " + id + " nao encontrado");
			}
		}
		
		return candidato;
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
