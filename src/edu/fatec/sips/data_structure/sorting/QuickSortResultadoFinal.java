package edu.fatec.sips.data_structure.sorting;

import edu.fatec.sips.model.ResultadoFinal;

public class QuickSortResultadoFinal {
	public final ResultadoFinal[] sort(final ResultadoFinal[] vetor, final int inicioOrdenacao, final int finalOrdenacao) {

		if (inicioOrdenacao < finalOrdenacao) {
			int pivo = partition(vetor, inicioOrdenacao, finalOrdenacao);

			sort(vetor, inicioOrdenacao, pivo - 1);
			sort(vetor, pivo + 1, finalOrdenacao);
		}

		return vetor;
	}

	private final int partition(final ResultadoFinal[] vetor, final int inicioOrdenacao, final int finalOrdenacao) {
		final ResultadoFinal pivo = vetor[finalOrdenacao];
		int indice = inicioOrdenacao - 1;

		for (int j = inicioOrdenacao; j < finalOrdenacao; j++) {

			if (vetor[j].getCandidato().getCurso().getId() < pivo.getCandidato().getCurso().getId()) {
				indice += 1;

				ResultadoFinal temp = vetor[indice];
				vetor[indice] = vetor[j];
				vetor[j] = temp;
			}
		}

		troca(vetor, indice, finalOrdenacao);

		return indice + 1;
	}

	private void troca(final ResultadoFinal[] vetor, final int indice, final int indiceMaior) {
		ResultadoFinal temp = vetor[indice + 1];
		vetor[indice + 1] = vetor[indiceMaior];
		vetor[indiceMaior] = temp;
	}
}
