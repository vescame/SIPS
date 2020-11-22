package edu.fatec.sips.data_structure;

import edu.fatec.sips.model.Documento;

public class NoArvoreDocumento {
	public Documento elemento;
	public NoArvoreDocumento esq, dir;

	public NoArvoreDocumento(Documento elemento) {
		this.elemento = elemento;
	}

	public NoArvoreDocumento(Documento elemento, NoArvoreDocumento esq, NoArvoreDocumento dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}
}
