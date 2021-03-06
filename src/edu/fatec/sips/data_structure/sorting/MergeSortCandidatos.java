package edu.fatec.sips.data_structure.sorting;

import edu.fatec.sips.model.Candidato;

public class MergeSortCandidatos {
	public final Candidato[] sort(final Candidato[] vetor) {
		if (vetor.length <= 1) {
			return vetor;
		}

		final int meioDoVetor = vetor.length / 2;
		Candidato[] elementosEsquerda = new Candidato[meioDoVetor];
		Candidato[] elementosDireita = new Candidato[vetor.length - meioDoVetor];

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

	private final Candidato[] merge(final Candidato[] elementosEsquerda, final Candidato[] elementosDireita) {
		final Candidato[] elementosOrdenados = new Candidato[elementosEsquerda.length + elementosDireita.length];
		
		int indiceEsquerda = 0;
		int indiceDireita = 0;
		int indiceResultado = 0;

		while (indiceEsquerda < elementosEsquerda.length && indiceDireita < elementosDireita.length) {
			Candidato candAuxEsq = elementosEsquerda[indiceEsquerda];
			Candidato candAuxDir = elementosDireita[indiceDireita];
			String nomeEsq = candAuxEsq.getNome() + " " + candAuxEsq.getSobrenome(); 
			String nomeDir = candAuxDir.getNome() + " " + candAuxDir.getSobrenome();
			if (nomeEsq.compareTo(nomeDir) < 1) {
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
