package edu.fatec.sips.controller;

import edu.fatec.sips.dao.DAOCandidato;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Documento;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CandidatoController {
	public DAOCandidato dao;
	
	public CandidatoController() {
		this.dao = new DAOCandidato();
	}
	
	public Candidato getPorId(int id) {
		return this.dao.buscarPorId(id);
	}
	
	public ListaLigadaSimples<Candidato> listarCandidatos() {
		return this.dao.listar();
	}
	
	public Candidato getPorDocumento(final Documento documento) {
		return this.dao.buscarCampo(documento);
	}
	
	public void salvar(final Candidato candidato) {
		this.dao.salvar(candidato);
	}
	
	public ListaLigadaSimples<Candidato> getPorNome(final String nome) {
		return this.dao.buscarPorNome(nome);
	}
	
	public void atualizar(final Candidato candidato) {
		this.dao.atualizar(candidato);
	}
	
	public Candidato remover(final Candidato elemento) {
		throw new NotImplementedException();
	}
}
