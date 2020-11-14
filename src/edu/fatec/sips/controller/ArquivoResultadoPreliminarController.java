package edu.fatec.sips.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Edital;
import edu.fatec.sips.model.ResultadoPreliminar;
import edu.fatec.sips.model.ResultadoPreliminar;

public class ArquivoResultadoPreliminarController {
	private final String ARQUIVO = "ArquivoResultadoPreliminar.txt";
	private final String SEPARADOR = ";";
	private final SimpleDateFormat sdf;
	private final CandidatoController candidatoController;
	private final EditalController editalController;

	public ArquivoResultadoPreliminarController() {
		this.candidatoController = new CandidatoController();
		this.editalController = new EditalController();
		this.sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

	public int ultimoId() throws IOException {
		String linha = new String();
		String linhaAnterior = new String();

		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			linhaAnterior = linha;
		}

		ResultadoPreliminar temp = quebrarAtributos(linhaAnterior);

		br.close();

		return temp.getId() + 1;
	}

	public ListaLigadaSimples<ResultadoPreliminar> listarResultado() throws IOException {
		String linha = new String();
		ListaLigadaSimples<ResultadoPreliminar> resultados = new ListaLigadaSimples<ResultadoPreliminar>();

		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			ResultadoPreliminar tmpResultado = quebrarAtributos(linha);
			resultados.inserirPrimeiro(tmpResultado);
		}

		br.close();

		return resultados;
	}

	private ResultadoPreliminar quebrarAtributos(String linha) {
		ResultadoPreliminar resultadoPreliminar = new ResultadoPreliminar();
		try {
			String[] atribs = linha.split(SEPARADOR);
			resultadoPreliminar.setId(Integer.valueOf(atribs[0]));
			Candidato candidato = this.candidatoController.getPorId(Integer.valueOf(atribs[1]));
			resultadoPreliminar.setCandidato(candidato);
			Edital edital = this.editalController.getPorId(Integer.valueOf(atribs[2]));
			resultadoPreliminar.setEdital(edital);
		} catch (Exception e) {
			System.out.println("falha ao obter atributo resultado final");
		}

		return resultadoPreliminar;
	}

	public void gravarCandidato(final ResultadoPreliminar resultado) throws IOException {
		FileWriter fw = new FileWriter(ARQUIVO, true);
		fw.write(concatenarCandidato(resultado) + "\n");
		fw.close();
	}

	private String concatenarCandidato(final ResultadoPreliminar resultado) {
		StringBuilder linha = new StringBuilder()
				.append(resultado.getId()).append(SEPARADOR);

		return linha.toString();
	}
}
