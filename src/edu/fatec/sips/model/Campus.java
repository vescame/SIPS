package edu.fatec.sips.model;

public class Campus {
	
	private int id;
	private String nome;
	private String unidade;

	public Campus(int id, String nome, String unidade) {
		this.id = id;
		this.nome = nome;
		this.unidade = unidade;
	}
	
	public Campus() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	@Override
	public String toString() {
		return "Campus [id: " + id + ", nome: " + nome + ", unidade: " + unidade + "]";
	}
	
}


