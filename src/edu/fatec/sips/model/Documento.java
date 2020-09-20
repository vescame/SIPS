package edu.fatec.sips.model;

public class Documento {
	private String titulo;
	private int numero;

	public Documento(String titulo, int numero) {
		super();
		this.titulo = titulo;
		this.numero = numero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
