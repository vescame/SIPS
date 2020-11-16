package edu.fatec.sips.controller;

import java.io.IOException;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.file_controller.ArquivoCursoController;
import edu.fatec.sips.model.Curso;

public class CursoController {
	private final ArquivoCursoController bdCursos;
	
	public CursoController() {
		this.bdCursos = new ArquivoCursoController();
	}
	
	public Curso getPorId(int id) {
		Curso encontrado = null;
		
		try {
			encontrado = this.bdCursos.buscarPorId(id);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (encontrado == null) {
				System.out.println("curso de id " + id + " nao encontrado");
			}
		}
		
		return encontrado;
	}
	
	public ListaLigadaSimples<Curso> listarCursos() {
		try {
			return this.bdCursos.listarCursos();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void salvar(final Curso curso) {
		try {
			this.bdCursos.gravarCurso(curso);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(final Curso curso) {
		try {
			this.bdCursos.atualizarCurso(curso);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Curso remover(final Curso curso) {
		Curso removido = null;
		
		try {
			removido = this.bdCursos.removerCurso(curso);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (removido == null) {
				System.out.println("curso de id " + curso.getId() + " nao existe");
			}
		}
		
		return removido;
	}
}
