package edu.fatec.sips.model;

public class ResultadoPreliminar {
	private int id;
	private Candidato candidato;
	private Edital edital;
	// O edital não é necessário, pois já tem esse dado no candidato
	
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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
