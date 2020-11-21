package edu.fatec.sips.data_structure.sorting;

import edu.fatec.sips.model.ResultadoPreliminar;

public class MergeSortResultadoPreliminarPorNota {
	public final ResultadoPreliminar[] sort(final ResultadoPreliminar[] vetor) {
		if (vetor.length <= 1) {
			return vetor;
		}

		final int meioDoVetor = vetor.length / 2;
		ResultadoPreliminar[] elementosEsquerda = new ResultadoPreliminar[meioDoVetor];
		ResultadoPreliminar[] elementosDireita = new ResultadoPreliminar[vetor.length - meioDoVetor];

		final int tamanhoEsquerda = elementosEsquerda.length;
		final int tamanhoDireita = elementosDireita.length;

		for (int i = 0; i < tamanhoEsquerda; ++i) {
			elementosEsquerda[i] = vetor[i];
		}

		for (int i = 0; i < tamanhoDireita; ++i) {
			elementosDireita[i] = vetor[i + tamanhoEsquerda];
		}

		elementosEsquerda = sort(elementosEsquerda);
		elementosDireita = sort(elementosDireita);

		return merge(elementosEsquerda, elementosDireita);
	}

	private final ResultadoPreliminar[] merge(final ResultadoPreliminar[] elementosEsquerda,
			final ResultadoPreliminar[] elementosDireita) {
		final ResultadoPreliminar[] elementosOrdenados = new ResultadoPreliminar[elementosEsquerda.length
				+ elementosDireita.length];

		int indiceEsquerda = 0;
		int indiceDireita = 0;
		int indiceResultado = 0;

		while (indiceEsquerda < elementosEsquerda.length && indiceDireita < elementosDireita.length) {
			ResultadoPreliminar resPrelimAuxEsq = elementosEsquerda[indiceEsquerda];
			ResultadoPreliminar resPrelimAuxDir = elementosDireita[indiceDireita];
			int idEditalEsq = resPrelimAuxEsq.getCandidato().getNota();
			int idEditalDir = resPrelimAuxDir.getCandidato().getNota();
			if (idEditalEsq > idEditalDir) {
				elementosOrdenados[indiceResultado++] = elementosEsquerda[indiceEsquerda++];
			} else {
				elementosOrdenados[indiceResultado++] = elementosDireita[indiceDireita++];
			}
		}

		while (indiceEsquerda < elementosEsquerda.length) {
			elementosOrdenados[indiceResultado++] = elementosEsquerda[indiceEsquerda++];
		}

		while (indiceDireita < elementosDireita.length) {
			elementosOrdenados[indiceResultado++] = elementosDireita[indiceDireita++];
		}

		return elementosOrdenados;
	}
}
