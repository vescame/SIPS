package edu.fatec.sips.controller;

import java.io.IOException;

import edu.fatec.sips.file_controller.ArquivoDocumentoCandidatoController;
import edu.fatec.sips.model.Documento;

public class DocumentoCandidatoController {
	private final ArquivoDocumentoCandidatoController daoDocumentos;
	
	public DocumentoCandidatoController() {
		this.daoDocumentos = new ArquivoDocumentoCandidatoController();
	}
	
	public Documento getPorNumeroDocumento(String numeroDocumento) {
		Documento documentoEncontrado = null;
		
		try {
			documentoEncontrado = this.daoDocumentos.getPorDocumento(numeroDocumento);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return documentoEncontrado;
	}
}
