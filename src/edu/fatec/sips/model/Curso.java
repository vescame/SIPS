package edu.fatec.sips.model;

public class Curso {
	
	private int id;
	private String sigla;
	private String nome;
	private String descricao;

	public Curso(int id, String sigla, String nome, String descricao) {
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Curso(int id, String sigla, String nome) {
		super();
		this.id = id;
		this.sigla = sigla;
		this.nome = nome;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", sigla=" + sigla + ", nome=" + nome + ", descricao=" + descricao + "]";
	}

	
}
