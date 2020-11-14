package edu.fatec.sips.model;

public class ResultadoPreliminar {
	private int id;
	private Candidato candidato;
	private Edital edital;
	private Curso curso;
	
	public Candidato getCandidato() {
		return candidato;
	}
	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}
	public Edital getEdital() {
		return edital;
	}
	public void setEdital(Edital edital) {
		this.edital = edital;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
