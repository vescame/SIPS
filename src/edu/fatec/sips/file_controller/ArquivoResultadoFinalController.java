package edu.fatec.sips.file_controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import edu.fatec.sips.controller.CandidatoController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.ResultadoFinal;

public class ArquivoResultadoFinalController {
	private final String ARQUIVO = "ArquivoResultadoFinal.txt";
	private final String SEPARADOR = ";";
	private final SimpleDateFormat sdf;
	private final CandidatoController ctrlCandidato;

	public ArquivoResultadoFinalController() {
		this.ctrlCandidato = new CandidatoController();
		this.sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

	public int ultimoId() throws IOException {
		String linha = new String();
		String linhaAnterior = new String();

		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			linhaAnterior = linha;
		}

		ResultadoFinal temp = quebrarAtributos(linhaAnterior);

		br.close();

		return temp.getId() + 1;
	}

	public ListaLigadaSimples<ResultadoFinal> listarResultado() throws IOException {
		String linha = new String();
		ListaLigadaSimples<ResultadoFinal> resultados = new ListaLigadaSimples<ResultadoFinal>();

		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			ResultadoFinal tmpResultado = quebrarAtributos(linha);
			resultados.adicionar(tmpResultado);
		}

		br.close();

		return resultados;
	}

	private ResultadoFinal quebrarAtributos(String linha) {
		ResultadoFinal resultado = new ResultadoFinal();
		try {
			String[] atribs = linha.split(SEPARADOR);
			resultado.setId(Integer.valueOf(atribs[0]));
			Candidato c = this.ctrlCandidato.getPorId(Integer.valueOf(atribs[1]));
			resultado.setCandidato(c);
			resultado.setDataAprovacao(this.sdf.parse(atribs[2]));
		} catch (Exception e) {
			System.out.println("falha ao obter atributo resultado final");
		}

		return resultado;
	}

	public void salvarResultadosFinais(final ListaLigadaSimples<ResultadoFinal> resultados) throws IOException {
		final int qtdResultados = resultados.getTamanho();
		FileWriter fw = new FileWriter(ARQUIVO, true);
		for (int i = 0; i < qtdResultados; ++i) {
			ResultadoFinal resFinal = resultados.espiar(i);
			resFinal.setId(i + 1);
			fw.write(concatenarResultado(resFinal) + "\n");
		}
		fw.close();
	}

	private String concatenarResultado(final ResultadoFinal resultado) {
		StringBuilder linha = new StringBuilder().append(resultado.getId()).append(SEPARADOR)
				.append(resultado.getCandidato().getId()).append(SEPARADOR)
				.append(this.sdf.format(resultado.getDataAprovacao())).append(SEPARADOR);

		return linha.toString();
	}
}
