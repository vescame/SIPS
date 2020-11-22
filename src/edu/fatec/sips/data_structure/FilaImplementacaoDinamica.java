package edu.fatec.sips.data_structure;

public class FilaImplementacaoDinamica <T> {

	private No<T> primeiro;
	private int tamanho;

	public FilaImplementacaoDinamica() {
		this.primeiro = null;
		this.tamanho = 0;
	}

	public void inserirNoFinal(T elemento) {
		if (estaVazia()) {
			No<T> no = new No<T>(elemento);
			primeiro = no;
			tamanho++;
		} else {

			No<T> aux = primeiro;
			while (aux.getProximo() != null) {
				aux = aux.getProximo();
			}
			No<T> novo = new No<T>(elemento);
			aux.setProximo(novo);
			tamanho++;
		}
	}

	public T removerNoInicio() {
		T elemento_removido = null;
		if (estaVazia()) {
			System.out.println("Impossível remover, a Fila está vazia");
		} else {
			elemento_removido = primeiro.getElemento();
			primeiro = primeiro.getProximo();
			tamanho--;
		}
		return elemento_removido;
	}

	public String printFila() {
		String result = "";
		No<T> aux = primeiro;
		while (aux != null) {
			result += aux.getElemento() + "";
			aux = aux.getProximo();
		}
		return result;
	}

	
	public T coletar(int posicao) {
		T elemento = null;	
		if (estaVazia()) {
			System.err.println("FILA VAZIA");
			return null;
		} else if (posicao < 0 || posicao >= tamanho) {
			System.err.println("POSICAO INVALIDA");
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
	
	public int getTamanho() {
		return this.tamanho;
	}

	public boolean estaVazia() {

		if (primeiro == null) {
			return true;
		}
		return false;
	}
	
}
