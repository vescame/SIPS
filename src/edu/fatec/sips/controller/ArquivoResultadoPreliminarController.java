package edu.fatec.sips.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.ResultadoPreliminar;

public class ArquivoResultadoPreliminarController {
	private final String ARQUIVO = "ArquivoResultadoPreliminar.txt";
	private final String SEPARADOR = ";";
	private final CandidatoController candidatoController;

	public ArquivoResultadoPreliminarController() {
		this.candidatoController = new CandidatoController();
	}

	public ResultadoPreliminar buscarPorId(final int id) throws IOException {
		ResultadoPreliminar encontrado = null;
		String linha = new String();

		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while (encontrado == null && (linha = br.readLine()) != null) {
			ResultadoPreliminar tempResultadoPreliminar = quebrarAtributos(linha);
			if (tempResultadoPreliminar.getId() == id) {
				encontrado = tempResultadoPreliminar;
			}
		}

		br.close();

		return encontrado;
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

	public ListaLigadaSimples<ResultadoPreliminar> listarResultadoPreliminar() throws IOException {
		String linha = new String();
		ListaLigadaSimples<ResultadoPreliminar> resultadoPreliminar = new ListaLigadaSimples<ResultadoPreliminar>();

		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			ResultadoPreliminar tmpResultado = quebrarAtributos(linha);
			resultadoPreliminar.inserirPrimeiro(tmpResultado);
		}

		br.close();

		return resultadoPreliminar;
	}

	private ResultadoPreliminar quebrarAtributos(String linha) {
		ResultadoPreliminar resultadoPreliminar = new ResultadoPreliminar();
		try {
			String[] atribs = linha.split(SEPARADOR);
			resultadoPreliminar.setId(Integer.valueOf(atribs[0]));
			Candidato candidato = this.candidatoController.getPorId(Integer.valueOf(atribs[1]));
			resultadoPreliminar.setCandidato(candidato);
		} catch (Exception e) {
			System.out.println("falha ao obter atributo resultado preliminar");
		}

		return resultadoPreliminar;
	}

	public void gravarResultadoPreliminar(final ResultadoPreliminar resultadoPreliminar) throws IOException {
		FileWriter fw = new FileWriter(ARQUIVO, true);
		fw.write(concatenarResultadoPreliminar(resultadoPreliminar) + "\n");
		fw.close();
	}

	private String concatenarResultadoPreliminar(final ResultadoPreliminar resultadoPreliminar) throws IOException {
		StringBuilder linha = new StringBuilder()
				.append(ultimoId()).append(SEPARADOR)
				.append(resultadoPreliminar.getCandidato().getId());

		return linha.toString();
	}

	public void atualizarResultadoPreliminar(final ResultadoPreliminar resultadoPreliminar) throws IOException {
		String linhaAtual = new String();

		File arquivoEntrada = new File(this.ARQUIVO);
		File arquivoTemporario = new File("tmp." + this.ARQUIVO);

		BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada));
		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemporario));

		while ((linhaAtual = br.readLine()) != null) {
			ResultadoPreliminar resultadoPreliminarAtual = quebrarAtributos(linhaAtual);

			if (resultadoPreliminarAtual.getId() == resultadoPreliminar.getId()) {
				resultadoPreliminarAtual = resultadoPreliminar;
			}

			bw.write(concatenarResultadoPreliminar(resultadoPreliminarAtual) + "\n");
		}

		bw.close();
		br.close();
		arquivoEntrada.delete();
		arquivoTemporario.renameTo(new File(this.ARQUIVO));
	}

	public ResultadoPreliminar removerPreliminar(final ResultadoPreliminar resultadoPreliminar) throws IOException {
		ResultadoPreliminar retorno = null;
		String linhaAtual = new String();

		File arquivoEntrada = new File(this.ARQUIVO);
		File arquivoTemporario = new File("tmp." + this.ARQUIVO);

		BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada));
		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemporario));

		while ((linhaAtual = br.readLine()) != null) {
			ResultadoPreliminar resultadoPreliminarAtual = quebrarAtributos(linhaAtual);

			if (resultadoPreliminarAtual.equals(resultadoPreliminar)) {
				retorno = resultadoPreliminarAtual;
			} else {
				bw.write(concatenarResultadoPreliminar(resultadoPreliminarAtual) + "\n");
			}
		}

		bw.close();
		br.close();
		arquivoEntrada.delete();
		arquivoTemporario.renameTo(new File(this.ARQUIVO));

		return retorno;
	}
}
