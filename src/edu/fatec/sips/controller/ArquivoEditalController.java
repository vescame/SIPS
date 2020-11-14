package edu.fatec.sips.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Campus;
import edu.fatec.sips.model.Curso;
import edu.fatec.sips.model.Edital;

public class ArquivoEditalController {
	
	private final String ARQUIVO = "ArquivoEdital.txt";
	private final String SEPARADOR = ";";
	private final CursoController cursoController;
	private final ArquivoCampusController arquivoCampusController;
	
	
		
	public ArquivoEditalController() {
		this.cursoController = new CursoController();
		this.arquivoCampusController = new ArquivoCampusController();
	}


	public int ultimoId() throws IOException {
		String linha = new String();
		String linhaAnterior = new String();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			linhaAnterior = linha;
		}
		
		Edital temp = quebrarAtributos(linhaAnterior);
		
		br.close();
		
		return temp.getId() + 1;
	}
	
	
	public Edital buscarPorId(final int id) throws IOException {
		Edital encontrado = null;
		String linha = new String();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while (encontrado == null && (linha = br.readLine()) != null) {
			Edital tempEdital = quebrarAtributos(linha);
			if (tempEdital.getId() == id) {
				encontrado = tempEdital;
			}
		}
		
		br.close();
		
		return encontrado;
	}
	
	public ListaLigadaSimples<Edital> listarEditais() throws IOException {
		String linha = new String();
		ListaLigadaSimples<Edital> editais = new ListaLigadaSimples<Edital>();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			Edital tempEdital = quebrarAtributos(linha);
			editais.adicionar(tempEdital);
		}
		
		br.close();
		
		return editais;
	}
	
	public void atualizarEdital(final Edital edital) throws Exception {
		String linhaAtual = new String();
		
		File arquivoEntrada = new File(this.ARQUIVO);
		File arquivoTemporario = new File("tmp." + this.ARQUIVO);

		BufferedReader br = new BufferedReader(new FileReader(arquivoEntrada));
		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemporario));

		while ((linhaAtual = br.readLine()) != null) {
			Edital editalAtual = quebrarAtributos(linhaAtual);
			
			if (editalAtual.getId() == edital.getId()) {
				editalAtual = edital;
			}
			
			bw.write(concatenarEdital(editalAtual) + "\n");
		}
		
		bw.close();
		br.close();
		arquivoEntrada.delete();
		arquivoTemporario.renameTo(new File(this.ARQUIVO));
	}
	
	public Edital removerEdital(final Edital edital) throws Exception {
		throw new Exception();
	}

	private Edital quebrarAtributos(String linha) {
		Edital edital = new Edital();
		try {
			String[] atribs = linha.split(SEPARADOR);
			edital.setId(Integer.valueOf(atribs[0]));
			edital.setTitulo(atribs[1]);
			int idCampus = Integer.valueOf(atribs[2]);
			Campus campus = arquivoCampusController.buscarPorId(idCampus);
			edital.setCampus(campus);
			int idCurso = Integer.valueOf(atribs[3]);
			Curso curso = cursoController.getPorId(idCurso);
			edital.setCurso(curso);
			edital.setPublicoAlvo(atribs[4]);
			edital.setPeriodoInicial(atribs[5]);
			edital.setPeriodoFinal(atribs[6]);
			edital.setAmplaConcorrencia(Integer.valueOf(atribs[7]));
			edital.setAcoesAfirmativas(Integer.valueOf(atribs[8]));
			edital.setDeficiente(Integer.valueOf(atribs[9]));
			edital.setCriterio(Integer.valueOf(atribs[10]));
			
		} catch (Exception e) {
			System.out.println("falha ao obter atributo edital");
		}
		
		return edital;
	}

	public void gravarEdital(final Edital edital) throws IOException {
		FileWriter fw = new FileWriter(ARQUIVO, true);
		fw.write(concatenarEdital(edital) + "\n");
		fw.close();
	}
	
	private String concatenarEdital(final Edital edital) throws IOException {
		StringBuilder linha = new StringBuilder()//.append(edital.getId()).append(SEPARADOR)
				.append(ultimoId()).append(SEPARADOR)
				.append(edital.getTitulo()).append(SEPARADOR)
				.append(edital.getCampus().getId()).append(SEPARADOR)
				.append(edital.getCurso().getId()).append(SEPARADOR)
				.append(edital.getPublicoAlvo()).append(SEPARADOR)
				.append(edital.getPeriodoInicial().toString()).append(SEPARADOR)
				.append(edital.getPeriodoFinal().toString()).append(SEPARADOR)
				.append(edital.getAmplaConcorrencia()).append(SEPARADOR)
				.append(edital.getAcoesAfirmativas()).append(SEPARADOR)
				.append(edital.getDeficiente()).append(SEPARADOR)
				.append(edital.getCriterio()).append(SEPARADOR);
		
		return linha.toString();
	}
}
