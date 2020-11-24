package edu.fatec.sips.data_structure.sorting;

import edu.fatec.sips.model.ResultadoFinal;

public class ShellSortResultadoFinalPorCurso {
	public void sort(ResultadoFinal vetor[]) {
		int n = vetor.length;

		for (int gap = n / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < n; i++) {
				ResultadoFinal res = vetor[i];
				int j = i;
				while (j >= gap && vetor[j - gap].getCandidato().getCurso().getSigla().compareTo(res.getCandidato().getCurso().getSigla()) >= 1) {
					vetor[j] = vetor[j - gap];
					j -= gap;
				}
				vetor[j] = res;
			}
		}
	}
}
