package edu.fatec.sips.data_structure.search;

import edu.fatec.sips.data_structure.No;
import edu.fatec.sips.model.ResultadoFinal;

public class BuscaBinariaResultadoFinalCandidato {
	private No<ResultadoFinal> encontrarMeio(No<ResultadoFinal> primeiro, No<ResultadoFinal> ultimo) {
		if (primeiro == null)
			return null;

		No<ResultadoFinal> anterior = primeiro;
		No<ResultadoFinal> atual = primeiro.getProximo();

		while (atual != ultimo) {
			atual = atual.getProximo();
			if (atual != ultimo) {
				anterior = anterior.getProximo();
				atual = atual.getProximo();
			}
		}

		return anterior;
	}

	public No<ResultadoFinal> buscaBinaria(No<ResultadoFinal> primeiro, Integer id) {
		No<ResultadoFinal> inicio = primeiro;
		No<ResultadoFinal> fim = null;

		do {
			No<ResultadoFinal> meio = encontrarMeio(inicio, fim);

			if (meio == null) {
				return null;
			}

			if (meio.getElemento().getCandidato().getId() == id) {
				return meio;
			} else if (meio.getElemento().getCandidato().getId() < id) {
				inicio = meio.getProximo();
			} else {
				fim = meio;
			}
		} while (fim == null || fim != inicio);

		return null;
	}
}
