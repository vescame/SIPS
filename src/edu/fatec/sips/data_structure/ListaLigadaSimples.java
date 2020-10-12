package edu.fatec.sips.data_structure;

public class ListaLigadaSimples<T> {
	private No<T> primeiro;
	private int tamanho;

	public ListaLigadaSimples() {
		this.primeiro = null;
		this.tamanho = 0;
	}
	
	public int getTamanho() {
		return this.tamanho;
	}

	public boolean estaVazia() {
		return this.tamanho == 0;
	}

	public void inserirPrimeiro(T elemento) {
		final No<T> no = new No<T>(elemento);
		no.setProximo(primeiro);
		primeiro = no;
		tamanho++;
	}

	public void adicionar(T elemento) {
		final No<T> no = new No<T>(elemento);
		if (estaVazia()) {
			inserirPrimeiro(elemento);
		} else {
			No<T> atual = primeiro;
			while (atual.getProximo() != null) {
				atual = atual.getProximo();
			}
			atual.setProximo(no);
			this.tamanho++;
		}
	}

	public void inserirNaPosicao(T elemento, int posicao) {
		final No<T> no = new No<T>(elemento);
		if (posicao < 0 || posicao >= tamanho) {
			System.err.println("POSICAO INVALIDA");
		} else {
			No<T> atual = primeiro;
			No<T> anterior = null;
			int posicaoAtual = 0;
			while (posicaoAtual < posicao) {
				anterior = atual;
				atual = atual.getProximo();
				posicaoAtual++;
			}
			no.setProximo(atual);
			anterior.setProximo(no);
			this.tamanho++;
		}
	}

	public T removerPrimeiro() {
		No<T> no = primeiro;
		if (estaVazia()) {
			System.err.println("LISTA VAZIAT");
		} else if (this.tamanho == 1) {
			this.primeiro = null;
			this.tamanho--;
		} else {
			this.primeiro = this.primeiro.getProximo();
			this.tamanho--;
		}

		return no.getElemento();
	}

	public T removerUltimo() {
		No<T> no = null;

		if (estaVazia()) {
			System.err.println("LISTA VAZIAT");
		} else if (tamanho == 1) {
			removerPrimeiro();
		} else {
			No<T> atual = this.primeiro;
			while (atual.getProximo().getProximo() != null) {
				atual = atual.getProximo();
			}
			no = atual.getProximo();
			atual.setProximo(null);
			this.tamanho--;
		}

		return no.getElemento();
	}

	public T removerNaPosicao(int posicao) {
		T elemento = null;

		if (estaVazia()) {
			System.err.println("LISTA VAZIA");
		} else if (posicao < 0 || posicao >= tamanho) {
			System.err.println("POSICAO INVALIDA");
		} else {
			if (posicao == 0) {
				elemento = removerPrimeiro();
			} else if (posicao == this.tamanho) {
				elemento = removerUltimo();
			} else {
				int currentIndex = 0;
				No<T> current = this.primeiro;
				while (currentIndex + 1 < posicao) {
					current = current.getProximo();
					currentIndex++;
				}
				elemento = current.getProximo().getElemento();
				current.setProximo(current.getProximo().getProximo());
				this.tamanho--;
			}
		}

		return elemento;
	}

	public T espiar(int posicao) {
		T elemento = null;

		if (estaVazia()) {
			System.err.println("LISTA VÁZIA !!!");
			return null;
		} else if (posicao < 0 || posicao >= tamanho) {
			System.err.println("POSICAO INVÁLIDA !!!");
		} else {
			No<T> no = primeiro;
			int currentIndex = 0;
			while (currentIndex < posicao) {
				no = no.getProximo();
				currentIndex++;
			}
			
			elemento = no.getElemento();
		}

		return elemento;
	}

	public void printar() {
		No<T> atual = this.primeiro;
		while (atual != null) {
			System.out.println(atual.getElemento());
			atual = atual.getProximo();
		}
	}
}
