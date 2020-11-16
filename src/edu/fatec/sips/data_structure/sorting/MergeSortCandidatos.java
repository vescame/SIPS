package edu.fatec.sips.data_structure.sorting;

import edu.fatec.sips.data_structure.No;
import edu.fatec.sips.model.Candidato;

public class MergeSortCandidatos {
	public No<Candidato> mergeSort(No<Candidato> prim) {
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
