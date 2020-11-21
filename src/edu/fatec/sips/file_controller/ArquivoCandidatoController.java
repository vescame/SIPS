package edu.fatec.sips.file_controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.data_structure.search.BuscaBinariaCandidato;
import edu.fatec.sips.data_structure.sorting.ShellSortCandidatos;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Curso;
import edu.fatec.sips.model.Edital;

public class ArquivoCandidatoController {
	private final ArquivoDocumentoCandidatoController documentos;
	private final ArquivoCursoController cursos;
	private final ArquivoRecursoController recursos;
	private final ArquivoEditalController editais;
	private final String ARQUIVO = "ArquivoCandidato.txt";
	private final String SEPARADOR = ";";
	private final SimpleDateFormat sdf;

	public ArquivoCandidatoController() {
		this.cursos = new ArquivoCursoController();
		this.documentos = new ArquivoDocumentoCandidatoController();
		this.recursos = new ArquivoRecursoController();
		this.editais = new ArquivoEditalController();
		this.sdf = new SimpleDateFormat("MM/dd/yyyy");
	}

	public int ultimoId() throws IOException {
		String linha = new String();
		String linhaAnterior = new String();

		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			linhaAnterior = linha;
		}

		Candidato temp = quebrarAtributos(linhaAnterior);

		br.close();

		return temp.getId() + 1;
	}

	public ListaLigadaSimples<Candidato> listarCandidatos() throws IOException {
		String linha = new String();
		ListaLigadaSimples<Candidato> candidatos = new ListaLigadaSimples<Candidato>();

		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			Candidato tempCandidato = quebrarAtributos(linha);
			candidatos.adicionar(tempCandidato);
		}

		br.close();

		return candidatos;
	}

	public void atualizarCandidato(final Candidato candidato) throws Exception {
		String linhaAtual = new String();

		File arquivoEntrada = new File(this.ARQUIVO);
		File arquivoTemporario = new File("tmp." + this.ARQUIVO);

		BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada));
		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemporario));

		while ((linhaAtual = br.readLine()) != null) {
			Candidato candidatoAtual = quebrarAtributos(linhaAtual);

			if (candidatoAtual.getId() == candidato.getId()) {
				candidatoAtual = candidato;
			}

			bw.write(concatenarCandidato(candidatoAtual) + "\n");
		}

		bw.close();
		br.close();
		arquivoEntrada.delete();
		arquivoTemporario.renameTo(new File(this.ARQUIVO));
	}

	public void atualizarCandidatos(final ListaLigadaSimples<Candidato> candidatos) throws Exception {
		String linhaAtual = new String();
		final int qtdCandidatos = candidatos.getTamanho();

		ShellSortCandidatos shellSort = new ShellSortCandidatos();

		shellSort.shellSort(candidatos.primeiro, qtdCandidatos);

		File arquivoEntrada = new File(this.ARQUIVO);
		File arquivoTemporario = new File("tmp." + this.ARQUIVO);

		BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada));
		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemporario));

		int i = 0;
		int j = 0;
		while ((linhaAtual = br.readLine()) != null) {
			Candidato candidatoAtual = quebrarAtributos(linhaAtual);

			if (i < qtdCandidatos) {
				Candidato candidato = new BuscaBinariaCandidato()
						.buscaBinaria(candidatos.primeiro, candidatos.espiar(j).getId()).getElemento();
				if (candidatoAtual.getId() == candidato.getId()) {
					candidatoAtual = candidato;
					j++;
				}
			}
			i++;
			bw.write(concatenarCandidato(candidatoAtual) + "\n");
		}

		bw.close();
		br.close();

		arquivoEntrada.delete();
		arquivoTemporario.renameTo(new File(this.ARQUIVO));
	}

	public Candidato removerCandidato(final Candidato candidato) throws Exception {
		throw new Exception();
	}

	private Candidato quebrarAtributos(String linha) {
		Candidato candidato = new Candidato();
		try {
			String[] atribs = linha.split(SEPARADOR);
			candidato.setId(Integer.valueOf(atribs[0]));
			candidato.setNome(atribs[1]);
			candidato.setSobrenome(atribs[2]);
			candidato.setDataNascimento(this.sdf.parse(atribs[3]));
			int idCurso = Integer.valueOf(atribs[4]);
			Curso curso = cursos.buscarPorId(idCurso);
			candidato.setCurso(curso);
			candidato.setAprovado(Boolean.valueOf(atribs[5]));
			candidato.setDesistente(Boolean.valueOf(atribs[6]));
			candidato.setDocumentos(documentos.listarDocumentos(candidato.getId()));
			candidato.setRecursos(this.recursos.buscarPorIdCandidato(candidato.getId()));
			Edital edital = this.editais.buscarPorId(Integer.valueOf(atribs[7]));
			candidato.setEdital(edital);
			candidato.setNota(Integer.valueOf(atribs[8]));
			candidato.setCriterio(Integer.valueOf(atribs[9]));
		} catch (Exception e) {
			System.out.println("falha ao obter atributo candidato");
		}

		return candidato;
	}

	public void gravarCandidato(final Candidato candidato) throws IOException {
		FileWriter fw = new FileWriter(ARQUIVO, true);
		fw.write(concatenarCandidato(candidato) + "\n");
		fw.close();
	}

	private String concatenarCandidato(final Candidato candidato) {
		StringBuilder linha = new StringBuilder().append(candidato.getId()).append(SEPARADOR)
				.append(candidato.getNome()).append(SEPARADOR).append(candidato.getSobrenome()).append(SEPARADOR)
				.append(sdf.format(candidato.getDataNascimento())).append(SEPARADOR)
				.append(candidato.getCurso().getId()).append(SEPARADOR).append(candidato.isAprovado()).append(SEPARADOR)
				.append(candidato.isDesistente()).append(SEPARADOR).append(candidato.getEdital().getId())
				.append(SEPARADOR).append(candidato.getNota()).append(SEPARADOR).append(candidato.getCriterio());

		return linha.toString();
	}
}
