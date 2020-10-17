package edu.fatec.sips.model;

import edu.fatec.sips.enums.Etapa;

public class Recurso {
	private int id;
	private int idCandidato;
	private String descricao;
	private Etapa etapaRecurso;
	private boolean aprovado;
	private boolean fechado;
	
	public Recurso() { /* vazio intecionalmente */ }
	
	public Recurso(final String descricao, final Etapa etapaRecurso) {
		this.descricao = descricao;
		this.etapaRecurso = etapaRecurso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(int idCandidato) {
		this.idCandidato = idCandidato;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Etapa getEtapaRecurso() {
		return etapaRecurso;
	}

	public void setEtapaRecurso(Etapa etapaRecurso) {
		this.etapaRecurso = etapaRecurso;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public boolean isFechado() {
		return fechado;
	}

	public void setFechado(boolean fechado) {
		this.fechado = fechado;
	}

	/**
	 * @returns descrição da solicitação do candidato
	 */
	@Override
	public String toString() {
		return this.descricao;
	}
}
