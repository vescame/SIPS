package edu.fatec.sips.model;

public class Curso {
	private String nome;
	private Campus campus;
	
	public Curso(final String nome, final Campus campus) {
		this.nome = nome;
		this.campus = campus;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
	}

	@Override
	public String toString() {
		return this.nome;
	}
}
