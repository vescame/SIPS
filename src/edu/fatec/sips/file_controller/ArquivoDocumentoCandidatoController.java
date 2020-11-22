package edu.fatec.sips.file_controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import edu.fatec.sips.data_structure.ArvoreBinaria;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Documento;

public class ArquivoDocumentoCandidatoController {
	private final String ARQUIVO = "ArquivoDocumentoCandidato.txt";
	private final String SEPARADOR = ";";

	public int ultimoId() throws IOException {
		String linha = new String();
		String linhaAnterior = new String();

		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			linhaAnterior = linha;
		}

		Documento tempDocumento = quebrarAtributos(linhaAnterior);

		br.close();

		return tempDocumento.getId() + 1;
	}

	public ArvoreBinaria carregarDocumentos() throws IOException {
		ArvoreBinaria documentos = new ArvoreBinaria();
		String linha = new String();

		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			Documento tempDocumento = quebrarAtributos(linha);
			documentos.inserir(documentos.raiz, tempDocumento);
		}

		br.close();

		return documentos;
	}

	public ListaLigadaSimples<Documento> listarDocumentos(final int idCandidato) throws IOException {
		ListaLigadaSimples<Documento> documentos = new ListaLigadaSimples<Documento>();
		String linha = new String();

		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			Documento tempDocumento = quebrarAtributos(linha);
			if (tempDocumento.getIdCandidato() == idCandidato) {
				documentos.adicionar(tempDocumento);
			}
		}

		br.close();

		return documentos;
	}

	public void gravarDocumentos(final ListaLigadaSimples<Documento> documentos) throws IOException {
		FileWriter fw = new FileWriter(ARQUIVO, true);

		for (int i = 0; i < documentos.getTamanho(); ++i) {
			fw.write(concatenarDocumento(documentos.espiar(i)) + "\n");
		}

		fw.close();
	}

	private Documento quebrarAtributos(String linha) {
		Documento documento = new Documento();
		try {
			String[] atribs = linha.split(SEPARADOR);
			documento.setId(Integer.valueOf(atribs[0]));
			documento.setIdCandidato(Integer.valueOf(atribs[1]));
			documento.setTitulo(atribs[2]);
			documento.setNumero(atribs[3]);
		} catch (Exception e) {
			System.out.println("falha ao obter atributo documento");
		}

		return documento;
	}

	private String concatenarDocumento(final Documento documento) {
		StringBuilder linha = new StringBuilder().append(documento.getId()).append(SEPARADOR)
				.append(documento.getIdCandidato()).append(SEPARADOR).append(documento.getTitulo()).append(SEPARADOR)
				.append(documento.getNumero());

		return linha.toString();
	}
}
