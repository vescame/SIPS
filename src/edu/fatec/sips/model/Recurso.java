package edu.fatec.sips.model;

import edu.fatec.sips.enums.Etapa;

public class Recurso {
	private Documento documentoCandidato;
	private String descricao;
	private Etapa etapaRecurso;
	
	public Recurso(final String descricao, final Etapa etapaRecurso) {
		this.descricao = descricao;
		this.etapaRecurso = etapaRecurso;
	}
	
	public Documento getDocumentoCandidato() {
		return documentoCandidato;
	}

	public void setDocumentoCandidato(Documento documentoCandidato) {
		this.documentoCandidato = documentoCandidato;
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

	/**
	 * @returns descrição da solicitação do candidato
	 */
	@Override
	public String toString() {
		return this.descricao;
	}
}
