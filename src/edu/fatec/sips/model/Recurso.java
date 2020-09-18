package edu.fatec.sips.model;

import edu.fatec.sips.model.base.DocumentoGenerico;
import edu.fatec.sips.tipos.Etapa;

public class Recurso {
	private DocumentoGenerico documentoCandidato;
	private String descricao;
	private Etapa etapaRecurso;
	
	public Recurso(final String descricao, final Etapa etapaRecurso) {
		this.descricao = descricao;
		this.etapaRecurso = etapaRecurso;
	}
	
	public DocumentoGenerico getDocumentoCandidato() {
		return documentoCandidato;
	}

	public void setDocumentoCandidato(DocumentoGenerico documentoCandidato) {
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
	 * @returns descri��o da solicita��o do candidato
	 */
	@Override
	public String toString() {
		return this.descricao;
	}
}