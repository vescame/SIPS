package edu.fatec.sips.data_structure;

import edu.fatec.sips.model.Documento;

public class ArvoreBinariaDocumentos {
	public NoArvoreDocumento raiz;

	public ArvoreBinariaDocumentos() {
		raiz = null;
	}

	public void inserir(NoArvoreDocumento no, Documento elemento) {
		if (no == null) {
			raiz = new NoArvoreDocumento(elemento);
		} else if (elemento.getNumero().compareTo(no.elemento.getNumero()) > 1) {
			if (no.esq != null) {
				inserir(no.esq, elemento);
			} else {
				no.esq = new NoArvoreDocumento(elemento);
			}
		} else {
			if (no.dir != null) {
				inserir(no.dir, elemento);
			} else {
				no.dir = new NoArvoreDocumento(elemento);
			}
		}
	}

	public int tamanho(NoArvoreDocumento no) {
		if (no == null) {
			return 0;
		} else {
			int esq = tamanho(no.esq);
			int dir = tamanho(no.dir);

			if (esq > dir) {
				esq++;
				return esq;
			} else {
				dir++;
				return dir;
			}
		}
	}

	public String mostrarArvore(NoArvoreDocumento no) {
		String retorno;
		retorno = "(";
		if (no != null) {
			retorno += " " + no.elemento + " ";
			retorno += mostrarArvore(no.esq);
			retorno += mostrarArvore(no.dir);
		}
		retorno = retorno + ") ";
		return retorno;
	}

	public void preOrdem(NoArvoreDocumento no) {
		if (no != null) {
			System.out.println(no.elemento + " ");
			preOrdem(no.esq);
			preOrdem(no.dir);
		}
	}

	public void posOrdem(NoArvoreDocumento no) {
		if (no != null) {
			posOrdem(no.esq);
			posOrdem(no.dir);
			System.out.println(no.elemento + " ");
		}
	}

	public void emOrdem(NoArvoreDocumento no) {
		if (no != null) {
			emOrdem(no.esq);
			System.out.println(no.elemento + " ");
			emOrdem(no.dir);
		}
	}

//	public Documento buscar(NoArvoreDocumento no, String documento) {
//		if (no != null) {
//			if (no.elemento.getNumero().equals(documento)) {
//				return no.elemento;
//			} else {
//				if (no.elemento.getNumero().compareTo(documento) > 1) {
//					return buscar(no.dir, documento);
//				} else {
//					return buscar(no.esq, documento);
//				}
//			}
//		}
//
//		return null;
//	}

	public Documento buscar(NoArvoreDocumento no, String documento) {
		if (no != null) {
			if (no.elemento.getNumero().equals(documento)) {
				return no.elemento;
			} else {
				if (documento.compareTo(no.elemento.getNumero()) > 1) {
					return buscar(no.esq, documento);
				} else {
					return buscar(no.dir, documento);
				}
			}
		}
		return null;
	}

	public NoArvoreDocumento menorElemento(NoArvoreDocumento root) {
		if (root.esq == null)
			return root;
		else {
			return menorElemento(root.esq);
		}
	}

	public NoArvoreDocumento remover(NoArvoreDocumento raiz, String documento) {
		if (raiz == null)
			return null;
		if (raiz.elemento.getNumero().compareTo(documento) < 1) {
			raiz.esq = remover(raiz.esq, documento);
		} else if (raiz.elemento.getNumero().compareTo(documento) > 1) {
			raiz.dir = remover(raiz.dir, documento);
		} else {
			if (raiz.esq != null && raiz.dir != null) {
				NoArvoreDocumento temp = raiz;
				NoArvoreDocumento minNodeForRight = menorElemento(temp.dir);
				raiz.elemento = minNodeForRight.elemento;
				raiz.dir = remover(raiz.dir, minNodeForRight.elemento.getNumero());
			} else if (raiz.esq != null) {
				raiz = raiz.esq;
			} else if (raiz.dir != null) {
				raiz = raiz.dir;
			} else
				raiz = null;
		}

		return null;
	}
}
