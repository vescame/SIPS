package edu.fatec.sips.model;

import edu.fatec.sips.data_structure.PilhaLigada;

public class Nota {
	private final int CASAS_DECIMAIS = 2;
	private PilhaLigada<Character> pilha = new PilhaLigada<Character>();
	private String nota = new String("00.00");

	public Nota(final float nota) {
		if (nota > 10.00f) {
			System.out.println("Nota invalida");
		} else {
			this.nota = formatar(String.valueOf(nota));
		}
	}

	public Nota(final double nota) {
		if (nota > 10.00d) {
			System.out.println("Nota invalida");
		} else {
			this.nota = formatar(String.valueOf(nota));
		}
	}

	private String formatar(final String nota) {
		return pushTodos(nota);
	}

	private String pushTodos(final String nota) {
		for (int i = nota.length() - 1; i >= 0; --i) {
			this.pilha.empilhar(nota.charAt(i));
		}

		return popFormatado(nota);
	}

	private String popFormatado(final String nota) {
		String notaFormatada = new String();
		int countAposPonto = 0;
		Character caractere;

		while ((caractere = pilha.desempilhar()) != '.') {
			notaFormatada = notaFormatada.concat(String.valueOf(caractere));
		}

		notaFormatada = notaFormatada.concat(String.valueOf(caractere));
		
		while (pilha.espiar() != null) {
			caractere = pilha.desempilhar();
			if (countAposPonto < this.CASAS_DECIMAIS) {
				notaFormatada = notaFormatada.concat(String.valueOf(caractere));
				countAposPonto++;
			}
		}

		return preencherCasasDecimais(notaFormatada, countAposPonto);
	}

	public String preencherCasasDecimais(String notaFormatada, int countAposPonto) {
		while (countAposPonto < 2) {
			notaFormatada = notaFormatada.concat("0");
			countAposPonto++;
		}

		return notaFormatada;
	}

	@Override
	public String toString() {
		return this.nota;
	}
}
