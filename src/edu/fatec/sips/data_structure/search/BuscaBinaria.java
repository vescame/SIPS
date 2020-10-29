package edu.fatec.sips.data_structure.search;

import edu.fatec.sips.data_structure.No;
import edu.fatec.sips.model.Candidato;

public class BuscaBinaria {
	public No<Candidato> encontrarMeio(No<Candidato> primeiro, No<Candidato> ultimo) {
		if (primeiro == null)
			return null;

		No<Candidato> anterior = primeiro;
		No<Candidato> atual = primeiro.getProximo();

		while (atual != ultimo) {
			atual = atual.getProximo();
			if (atual != ultimo) {
				anterior = anterior.getProximo();
				atual = atual.getProximo();
			}
		}

		return anterior;
	}

	public No<Candidato> buscaBinaria(No<Candidato> primeiro, Integer id) {
		No<Candidato> inicio = primeiro;
		No<Candidato> fim = null;

		do {
			No<Candidato> meio = encontrarMeio(inicio, fim);

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
