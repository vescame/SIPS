package edu.fatec.sips.data_structure.sorting;

import edu.fatec.sips.data_structure.No;
import edu.fatec.sips.model.Candidato;

public class ShellSortCandidatos {
	public No<Candidato> primeiro;
	private int tamanho;

	public ShellSortCandidatos() {
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
			System.err.println("LISTA VAZIAT");
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
			System.err.println("LISTA VÁZIA !!!");
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

	public void shellSort() {
		if (this.primeiro != null) {
			int intervalo = 0;
			int comprimento = this.getTamanho();
			No<Candidato> p = primeiro;
			
			while (2 * (3 * intervalo + 1) <= comprimento) {
				intervalo = 3 * intervalo + 1;
			}
			
			for (int h = intervalo; intervalo > 0; intervalo /= 3) {
				for (int i = h; i > 0; i--) {
					for (int j = h - i; j < comprimento; j += intervalo) {
						p = this.primeiro;
						
						int n = 0;
						for (int k = 0; k < j; k++) {
							p = p.getProximo();
							n = k;
						}
						
						No<Candidato> c = p;
						
						int temp = n + intervalo;
						
						while (c != null) {
							for (int l = n; l < temp; l++) {
								if (c != null) {
									n++;
									c = c.getProximo();
								} else {
									break;
								}
							}

							if (c != null) {
								if (p.getElemento().getId() > c.getElemento().getId()) {
									Candidato t = p.getElemento();
									p.setElemento(c.getElemento());
									c.setElemento(t);
								}
							}
							temp += intervalo;
						}
					}
				}
			}
		}
	}
}
