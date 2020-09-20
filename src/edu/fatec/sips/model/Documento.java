package edu.fatec.sips.model;

public class Documento {
	private String titulo;
	private String numero;
	
	// numero deve ser String pois h� documentos que utilizam
	// tanto n�meros como letras. n�o ser�o realizadas opera��es
	// envolvendo c�lculos com os documentos
	public Documento(String titulo, String numero) {
		this.titulo = titulo;
		this.numero = numero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
