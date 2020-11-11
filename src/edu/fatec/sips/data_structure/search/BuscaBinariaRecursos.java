package edu.fatec.sips.data_structure.search;

import edu.fatec.sips.data_structure.No;
import edu.fatec.sips.model.Recurso;

public class BuscaBinariaRecursos {
	public No<Recurso> encontrarMeio(No<Recurso> primeiro, No<Recurso> ultimo) {
		if (primeiro == null)
			return null;

		No<Recurso> anterior = primeiro;
		No<Recurso> atual = primeiro.getProximo();

		while (atual != ultimo) {
			atual = atual.getProximo();
			if (atual != ultimo) {
				anterior = anterior.getProximo();
				atual = atual.getProximo();
			}
		}

		return anterior;
	}

	public No<Recurso> buscaBinaria(No<Recurso> primeiro, Integer id) {
		No<Recurso> inicio = primeiro;
		No<Recurso> fim = null;

		do {
			No<Recurso> meio = encontrarMeio(inicio, fim);

			if (meio == null) {
				return null;
			}

			if (meio.getElemento().getId() == id) {
				return meio;
			} else if (meio.getElemento().getId() < id) {
				inicio = meio.getProximo();
			} else {
				fim = meio;
			}
		} while (fim == null || fim != inicio);

		return null;
	}
}
