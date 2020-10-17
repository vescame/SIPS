package edu.fatec.sips.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Campus;

public class ArquivoCampusController {
	private final String ARQUIVO = "ArquivoCampus.txt";
	private final String SEPARADOR = ";";
	
	public int ultimoId() throws IOException {
		String linha = new String();
		String linhaAnterior = new String();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			linhaAnterior = linha;
		}
		
		Campus temp = quebrarAtributos(linhaAnterior);
		
		br.close();
		
		return temp.getId() + 1;
	}
	
	private Campus quebrarAtributos(String linha) {
		Campus campus = new Campus();
		try {
			String[] atribs = linha.split(SEPARADOR);
			campus.setId(Integer.valueOf(atribs[0]));
			campus.setNome(atribs[1]);
			campus.setUnidade(atribs[2]);
		} catch (Exception e) {
			System.out.println("falha ao obter atributo campus");
		}
		
		return campus;
	}

	public ListaLigadaSimples<Campus> listarCampus() throws IOException {
		String linha = new String();
		ListaLigadaSimples<Campus> campus = new ListaLigadaSimples<Campus>();

		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			Campus tempCampus = quebrarAtributos(linha);
			campus.adicionar(tempCampus);
		}

		br.close();

		return campus;
	}

	public void gravarCampus(final Campus campus) throws IOException {
		campus.setId(ultimoId());
		FileWriter fw = new FileWriter(ARQUIVO, true);
		fw.write(concatenarCampus(campus) + "\n");
		fw.close();
	}

	private String concatenarCampus(final Campus campus) {
		StringBuilder linha = new StringBuilder().append(campus.getId()).append(SEPARADOR).append(campus.getNome())
				.append(SEPARADOR).append(campus.getUnidade());
		return linha.toString();
	}
}
