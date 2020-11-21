package edu.fatec.sips.model;

import java.util.Date;

public class ResultadoFinal {
	private int id;
	private Candidato candidato;
	private Date dataAprovacao;
	
	public ResultadoFinal() { }
	
	public ResultadoFinal(final Candidato candidato) {
		this.candidato = candidato;
		this.dataAprovacao = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public boolean isAprovado() {
		return this.candidato.isAprovado();
	}

	public void setAprovado(boolean aprovado) {
		this.candidato.setAprovado(aprovado);
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

}
