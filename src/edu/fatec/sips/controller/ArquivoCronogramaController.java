package edu.fatec.sips.controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import edu.fatec.sips.data_structure.FilaImplementacaoDinamica;
import edu.fatec.sips.model.CronogramaDeAtividades;

public class ArquivoCronogramaController {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private final String arquivoCronograma = "ArquivoCronograma.txt";
	private final String SEPARATOR = ";";

	public void gravar(CronogramaDeAtividades atividade) throws IOException {
		FileWriter fw = new FileWriter(arquivoCronograma, true);
		fw.write(concatenarAtividades(atividade) + "\n");
		fw.close();
	}

	private CronogramaDeAtividades quebrarAtributos(String linha) {
		CronogramaDeAtividades atividades = new CronogramaDeAtividades();
		try {
			String[] atributos = linha.split(SEPARATOR);
			atividades.setIdAtividade(Integer.valueOf(atributos[0]));
			atividades.setDescricaoAtividade(atributos[1]);
			atividades.setDataInicio(sdf.parse(atributos[2]));
			atividades.setDataEntrega(sdf.parse(atributos[3]));
		} catch (Exception e) {
			System.out.println("Falha ao obter atributo cronograma");
		}
		return atividades;
	}

	public FilaImplementacaoDinamica<CronogramaDeAtividades> listarAtividades() throws IOException {
		String linha = new String();
		FilaImplementacaoDinamica<CronogramaDeAtividades> atividades = new FilaImplementacaoDinamica<CronogramaDeAtividades>();
		BufferedReader read = new BufferedReader(new FileReader(arquivoCronograma));
		while ((linha = read.readLine()) != null) {
			CronogramaDeAtividades cronoTemp = quebrarAtributos(linha);
			atividades.inserirNoFinal(cronoTemp);
		}
		read.close();
		return atividades;
	}

	public String concatenarAtividades(CronogramaDeAtividades atividade) {
		StringBuilder linha = new StringBuilder().append(atividade.getIdAtividade()).append(SEPARATOR)
				.append(atividade.getDescricaoAtividade()).append(SEPARATOR)
				.append(sdf.format(atividade.getDataInicio())).append(SEPARATOR)
				.append(sdf.format(atividade.getDataEntrega()));
		return linha.toString();
	}

	public void abrirArquivo() throws IOException {

		File file = new File(arquivoCronograma);
		if (!Desktop.isDesktopSupported()) {
			System.out.println("Arquivo não suportado");
		}
		Desktop desktop = Desktop.getDesktop();
		if (file.exists()) {
			desktop.open(file);
		} else {
			throw new IOException();
		}

	}

}
