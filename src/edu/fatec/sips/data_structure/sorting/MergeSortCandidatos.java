package edu.fatec.sips.data_structure.sorting;

import edu.fatec.sips.data_structure.No;
import edu.fatec.sips.model.Candidato;

public class MergeSortCandidatos {
	public No<Candidato> primeiro;
	private int tamanho;

	public MergeSortCandidatos() {
		this.primeiro = null;
		this.tamanho = 0;
	}
	
	public int getTamanho() {
		return this.tamanho;
	}

	public boolean estaVazia() {
		return this.tamanho == 0;
	}

	public void inserirPrimeiro(Candidato elemento) {
		final No<Candidato> no = new No<Candidato>(elemento);
		no.setProximo(primeiro);
		primeiro = no;
		tamanho++;
	}

	public void adicionar(Candidato elemento) {
		final No<Candidato> no = new No<Candidato>(elemento);
		if (estaVazia()) {
			inserirPrimeiro(elemento);
		} else {
			No<Candidato> atual = primeiro;
			while (atual.getProximo() != null) {
				atual = atual.getProximo();
			}
			atual.setProximo(no);
			this.tamanho++;
		}
	}

	public void inserirNaPosicao(Candidato elemento, int posicao) {
		final No<Candidato> no = new No<Candidato>(elemento);
		if (posicao < 0 || posicao >= tamanho) {
			System.err.println("POSICAO INVALIDA");
		} else {
			No<Candidato> atual = primeiro;
			No<Candidato> anterior = null;
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

	public Candidato removerPrimeiro() {
		No<Candidato> no = primeiro;
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

	public Candidato removerUltimo() {
		No<Candidato> no = null;

		if (estaVazia()) {
			System.err.println("LISTA VAZIA");
		} else if (tamanho == 1) {
			removerPrimeiro();
		} else {
			No<Candidato> atual = this.primeiro;
			while (atual.getProximo().getProximo() != null) {
				atual = atual.getProximo();
			}
			no = atual.getProximo();
			atual.setProximo(null);
			this.tamanho--;
		}

		return no.getElemento();
	}

	public Candidato removerNaPosicao(int posicao) {
		Candidato elemento = null;

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
				No<Candidato> current = this.primeiro;
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

	public Candidato espiar(int posicao) {
		Candidato elemento = null;

		if (estaVazia()) {
			System.err.println("LISTA VAZIA !!!");
			return null;
		} else if (posicao < 0 || posicao >= tamanho) {
			System.err.println("POSICAO INVÁLIDA !!!");
		} else {
			No<Candidato> no = primeiro;
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
		No<Candidato> atual = this.primeiro;
		while (atual != null) {
			System.out.println(atual.getElemento());
			atual = atual.getProximo();
		}
	}
	
	public void mergeSort() {
		this.primeiro = this.mergeSort(this.primeiro);
	}
	
	private No<Candidato> mergeSort(No<Candidato> prim) {
		if (prim == null || prim.getProximo() == null)
			return prim;

		No<Candidato> noDoMeio = meioDaLista(prim);
		No<Candidato> proxNoAposMeio = noDoMeio.getProximo();
		
		noDoMeio.setProximo(null);
		
		No<Candidato> noEsquerdo = mergeSort(prim);
		
		No<Candidato> noDireito = mergeSort(proxNoAposMeio);
		
		return merge(noEsquerdo, noDireito);
	}
	
	private No<Candidato> merge(No<Candidato> prim, No<Candidato> seg) {
		No<Candidato> no = null;
		
		if (prim == null) {
			return seg;
		}
		
		if (seg == null) {
			return prim;
		}
		
		String nomePrimCandidato = prim.getElemento().getNome() + " " + prim.getElemento().getSobrenome();
		String nomeSegCandidato = seg.getElemento().getNome() + " " + seg.getElemento().getSobrenome();
		if (nomePrimCandidato.compareTo(nomeSegCandidato) < 1) {
			no = prim;
			no.setProximo(merge(prim.getProximo(), seg));
		} else {
			no = seg;
			no.setProximo(merge(seg.getProximo(), prim));
		}

		return no;
	}

	private No<Candidato> meioDaLista(No<Candidato> primeiro) {
		if (primeiro == null)
			return primeiro;

		No<Candidato> atual = primeiro.getProximo();
		No<Candidato> anterior = primeiro;

		while (atual != null) {
			atual = atual.getProximo();
			if (atual != null) {
				anterior = anterior.getProximo();
				atual = atual.getProximo();
			}
		}

		return anterior;
	}
}
