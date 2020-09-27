package edu.fatec.sips.data_structure;

public class FilaImplementacaoDinamica <T> {

	private No<T> primeiro;
	private int tamanho;

	public FilaImplementacaoDinamica() {
		this.primeiro = null;
		this.tamanho = 0;
	}

	public void inserirNoFinal(T elemento) {
		if (isEmpty()) {
			No<T> no = new No<T>(elemento);
			primeiro = no;
		} else {

			No<T> aux = primeiro;
			while (aux.getProximo() != null) {
				aux = aux.getProximo();
			}
			No<T> novo = new No<T>(elemento);
			aux.setProximo(novo);
		}
	}

	public T removerNoInicio() {
		T elemento_removido = null;
		if (isEmpty()) {
			System.out.println("Impossível remover, a Fila está vazia");
		} else {
			elemento_removido = primeiro.getElemento();
			primeiro = primeiro.getProximo();
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

	public int getSize() {
		return this.tamanho;
	}

	public boolean isEmpty() {

		if (primeiro == null) {
			return true;
		}
		return false;
	}
	
}
