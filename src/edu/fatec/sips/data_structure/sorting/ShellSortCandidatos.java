package edu.fatec.sips.data_structure.sorting;

import edu.fatec.sips.model.Candidato;

public class ShellSortCandidatos {
	public void sort(Candidato vetorCandidatos[]) {
		int n = vetorCandidatos.length;

		for (int gap = n / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < n; i++) {
				Candidato c = vetorCandidatos[i];
				int j = i;
				while (j >= gap && vetorCandidatos[j - gap].getId() > c.getId()) {
					vetorCandidatos[j] = vetorCandidatos[j - gap];
					j -= gap;
				}
				vetorCandidatos[j] = c;
			}
		}
	}
}
