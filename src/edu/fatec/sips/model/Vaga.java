package edu.fatec.sips.model;

public class Vaga {
	private int id;
	private String tipo;
	private int quantidade;
	private String publicoAlvo;
	private String descricao;

	
	public Vaga(int id, String tipo, int quantidade, String publicoAlvo, String descricao) {
		this.id = id;
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.publicoAlvo = publicoAlvo;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getPublicoAlvo() {
		return publicoAlvo;
	}
	public void setPublicoAlvo(String publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Vaga [id=" + id + ", tipo=" + tipo + ", quantidade=" + quantidade + ", publicoAlvo=" + publicoAlvo
				+ ", descricao=" + descricao + "]";
	}
	
}
