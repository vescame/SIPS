package edu.fatec.sips.data_structure.sorting;

import edu.fatec.sips.model.Edital;
import edu.fatec.sips.model.ResultadoPreliminar;

public class QuickSortResultadoPreliminarEdital {
	public final ResultadoPreliminar[] sort(final ResultadoPreliminar[] vetor, final int inicioOrdenacao,
			final int finalOrdenacao) {

		if (inicioOrdenacao < finalOrdenacao) {
			int pivo = partition(vetor, inicioOrdenacao, finalOrdenacao);

			sort(vetor, inicioOrdenacao, pivo - 1);
			sort(vetor, pivo + 1, finalOrdenacao);
		}

		return vetor;
	}

	private final int partition(final ResultadoPreliminar[] vetor, final int inicioOrdenacao,
			final int finalOrdenacao) {
		final ResultadoPreliminar pivo = vetor[finalOrdenacao];
		int indice = inicioOrdenacao - 1;

		for (int j = inicioOrdenacao; j < finalOrdenacao; j++) {

			Edital editalJ = vetor[j].getCandidato().getEdital();
			Edital editalPivo = pivo.getCandidato().getEdital();
			if ((editalJ != null && editalPivo != null) && editalJ.getId() < editalPivo.getId()) {
				indice += 1;

				ResultadoPreliminar temp = vetor[indice];
				vetor[indice] = vetor[j];
				vetor[j] = temp;
			}
		}

		troca(vetor, indice, finalOrdenacao);

		return indice + 1;
	}

	private void troca(final ResultadoPreliminar[] vetor, final int indice, final int indiceMaior) {
		ResultadoPreliminar temp = vetor[indice + 1];
		vetor[indice + 1] = vetor[indiceMaior];
		vetor[indiceMaior] = temp;
	}

}
