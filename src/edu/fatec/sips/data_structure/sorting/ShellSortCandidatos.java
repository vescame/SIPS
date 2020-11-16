package edu.fatec.sips.data_structure.sorting;

import edu.fatec.sips.data_structure.No;
import edu.fatec.sips.model.Candidato;

public class ShellSortCandidatos {

	public void shellSort(No<Candidato> primeiro, int tamanho) {
		if (primeiro != null) {
			int intervalo = 0;
			int comprimento = tamanho;
			No<Candidato> p = primeiro;

			while (2 * (3 * intervalo + 1) <= comprimento) {
				intervalo = 3 * intervalo + 1;
			}

			for (int h = intervalo; intervalo > 0; intervalo /= 3) {
				for (int i = h; i > 0; i--) {
					for (int j = h - i; j < comprimento; j += intervalo) {
						p = primeiro;

						int n = 0;
						for (int k = 0; k < j; k++) {
							p = p.getProximo();
							n = k;
						}

						No<Candidato> c = p;

						int temp = n + intervalo;

						while (c != null) {
							for (int l = n; l < temp; l++) {
								if (c != null) {
									n++;
									c = c.getProximo();
								} else {
									break;
								}
							}

							if (c != null) {
								if (p.getElemento().getId() > c.getElemento().getId()) {
									Candidato t = p.getElemento();
									p.setElemento(c.getElemento());
									c.setElemento(t);
								}
							}
							temp += intervalo;
						}
					}
				}
			}
		}
	}
}
