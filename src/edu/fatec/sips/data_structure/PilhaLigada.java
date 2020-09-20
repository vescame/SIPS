package edu.fatec.sips.data_structure;

public class PilhaLigada<T> {
	private No<T> topo;
	private int tamanho;

	public PilhaLigada() {
		topo = null;
		tamanho = 0;
	}

	public boolean estaVazia() {
		return topo == null;
	}

	public final int getTamanho() {
		return tamanho;
	}

	public final T espiar() {
		T element = null;
		if (estaVazia()) {
			System.out.println("PILHA VAZIA");
		} else {
			element = topo.getElemento();
		}

		return element;
	}

	public void empilhar(final T elemento) {
		final No<T> no = new No<T>(elemento);
		if (estaVazia()) {
			topo = no;
		} else {
			no.setProximo(topo);
			topo = no;
		}

		tamanho++;
	}

	public final T desempilhar() {
		T elemento = null;

		if (estaVazia()) {
			System.out.println("PILHA VAZIA");
		} else {
			elemento = topo.getElemento();
			topo = topo.getProximo();
			tamanho--;
		}

		return elemento;
	}
}
