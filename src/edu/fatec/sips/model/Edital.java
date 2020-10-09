package edu.fatec.sips.model;

import java.time.LocalDate;

public class Edital {
	private int id;
	private String titulo;
	private String curso;
	private String publicoAlvo;
	private String periodoInicial;
	private String periodoFinal;
	private int qtdVagas;
	private String tipoVaga;
	private int criterio;
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getPublicoAlvo() {
		return publicoAlvo;
	}

	public void setPublicoAlvo(String publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
	}

	public String getPeriodoInicial() {
		return periodoInicial;
	}

	public void setPeriodoInicial(String string) {
		this.periodoInicial = string;
	}

	public String getPeriodoFinal() {
		return periodoFinal;
	}



	public void setPeriodoFinal(String string) {
		this.periodoFinal = string;
	}

	public int getQtdVagas() {
		return qtdVagas;
	}

	public void setQtdVagas(int qtdVagas) {
		this.qtdVagas = qtdVagas;
	}

	public String getTipoVaga() {
		return tipoVaga;
	}

	public void setTipoVaga(String tipoVaga) {
		this.tipoVaga = tipoVaga;
	}

	public int getCriterio() {
		return criterio;
	}

	public void setCriterio(int criterio) {
		this.criterio = criterio;
	}

	@Override
	public String toString() {
		return "Edital\nid=" + id + "\nTitulo=" + titulo + " " + "\nCurso=" + curso + " " + 
				"\nPublico Alvo=" + publicoAlvo + " " + "\nPeriodo Inicial=" + periodoInicial + " " +
				"\nPeriodo Final=" + periodoFinal + " " + "\nQuantidade de vagas=" + qtdVagas + " " +
				"\nTipo de vaga=" + tipoVaga + " " + "\nCriterio=" + criterio;
	}	
	
	
	
}
