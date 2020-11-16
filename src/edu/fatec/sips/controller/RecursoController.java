package edu.fatec.sips.controller;

import java.io.IOException;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.file_controller.ArquivoRecursoController;
import edu.fatec.sips.model.Recurso;

public class RecursoController {
	private final ArquivoRecursoController dao;
	
	public RecursoController() {
		this.dao = new ArquivoRecursoController();
	}
	
	public Recurso getPorId(final int idRecurso) {
		try {
			return this.dao.buscarPorId(idRecurso);
		} catch (IOException e) {
			System.out.println("Recurso inexistente");
		}
		
		return null;
	}
	
	public void abrirRecurso(final Recurso recurso) {
		try {
			this.dao.gravarRecurso(recurso);
		} catch (IOException e) {
			System.out.println("Falha ao abrir recurso");
		}
	}
	
	public ListaLigadaSimples<Recurso> listarRecursosAbertos() {
		try {
			return this.dao.listarRecursos();
		} catch (IOException e) {
			System.out.println("Falha ao abrir recurso");
		}
		
		return null;
	}
	
	public ListaLigadaSimples<Recurso> listarRecursosPorCandidato(int idCandidato) {
		try {
			return this.dao.buscarPorIdCandidato(idCandidato);
		} catch (IOException e) {
			System.out.println("Falha ao abrir recurso");
		}
		
		return null;
	}
	
	public void aprovarRecurso(final int idRecurso) {
		try {
			Recurso recurso = this.dao.buscarPorId(idRecurso);
			recurso.setAprovado(true);
			recurso.setFechado(true);
			this.dao.atualizarRecurso(recurso);
		} catch (IOException e) {
			System.out.println("Recurso falhou ao aprovar");
		}
	}
	
	public void reprovarRecurso(final int idRecurso) {
		try {
			Recurso recurso = this.dao.buscarPorId(idRecurso);
			recurso.setAprovado(false);
			recurso.setFechado(true);
			this.dao.atualizarRecurso(recurso);
		} catch (IOException e) {
			System.out.println("Recurso falhou ao reprovar");
		}
	}
}
