package edu.fatec.sips.controller;

import java.io.IOException;

import edu.fatec.sips.data_structure.ArvoreBinariaDocumentos;
import edu.fatec.sips.file_controller.ArquivoDocumentoCandidatoController;
import edu.fatec.sips.model.Documento;

public class DocumentoCandidatoController {
	private final ArquivoDocumentoCandidatoController daoDocumentos;
	private final ArvoreBinariaDocumentos arvoreDocumentos;

	public DocumentoCandidatoController() {
		this.daoDocumentos = new ArquivoDocumentoCandidatoController();
		ArvoreBinariaDocumentos tmp = null;
		try {
			tmp = this.daoDocumentos.carregarDocumentos();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.arvoreDocumentos = tmp;
	}

	public Documento getPorNumeroDocumento(String documento) {
		Documento documentoEncontrado = null;
		documentoEncontrado = this.arvoreDocumentos.buscar(this.arvoreDocumentos.raiz, documento);
		return documentoEncontrado;
	}
}
