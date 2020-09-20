package edu.fatec.sips.data_structure;

public class No<T> {
	private No<T> proximo;
	private T elemento;

	public No(T elemento) {
		this.elemento = elemento;
		this.proximo = null;
	}

	public No(T elemento, No<T> no) {
		this.elemento = elemento;
		proximo = no;
	}

	public T getElemento() {
		return elemento;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	public No<T> getProximo() {
		return proximo;
	}

	public void setProximo(No<T> elemento) {
		this.proximo = elemento;
	}
}
