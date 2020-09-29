package edu.fatec.sips.model;

public class Curso {
	
	private int id;
	private String sigla;
	private String nome;
	private String descricao;
	
	public Curso() { /* vazio intencionalmente */ }

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
		return this.sigla + " - " + this.nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {

			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
			
		
		Curso other = (Curso) obj;
		
		if (descricao == null) {
			if (other.descricao != null) {
				return false;
			}
		} else if (!descricao.toLowerCase().equals(other.descricao.toLowerCase())) {
			return false;
		}
		
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.toLowerCase().equals(other.nome.toLowerCase())) {
			return false;
		}
		
		if (sigla == null) {
			if (other.sigla != null) {
				return false;
			}
		} else if (!sigla.toLowerCase().equals(other.sigla.toLowerCase())) {
			return false;
		}
		
		return true;
	}
	
}
