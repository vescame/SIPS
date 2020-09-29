package edu.fatec.sips.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Curso;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ArquivoCursoController {
	private final String ARQUIVO = "ArquivoCurso.txt";
	private final String SEPARADOR = ";";
	
	public int ultimoId() throws IOException {
		String linha = new String();
		String linhaAnterior = new String();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			linhaAnterior = linha;
		}
		
		Curso temp = quebrarAtributos(linhaAnterior);
		
		br.close();
		
		return temp.getId() + 1;
	}
	
	public Curso buscarPorId(final int id) throws IOException {
		Curso encontrado = null;
		String linha = new String();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while (encontrado == null && (linha = br.readLine()) != null) {
			Curso tempCurso = quebrarAtributos(linha);
			if (tempCurso.getId() == id) {
				encontrado = tempCurso;
			}
		}
		
		br.close();
		
		return encontrado;
	}
	
	public ListaLigadaSimples<Curso> listarCursos() throws IOException {
		String linha = new String();
		ListaLigadaSimples<Curso> cursos = new ListaLigadaSimples<Curso>();
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			Curso tempCurso = quebrarAtributos(linha);
			cursos.adicionar(tempCurso);
		}
		
		br.close();
		
		return cursos;
	}
	
	private boolean cursoExisteNoArquivo(Curso curso) throws IOException {
		String linha = new String();
		boolean cursoExiste = false;
		
		BufferedReader br = new BufferedReader(new FileReader(ARQUIVO));

		while ((linha = br.readLine()) != null) {
			Curso tempCurso = quebrarAtributos(linha);
			if (curso.equals(tempCurso)) {
				cursoExiste = !cursoExiste;
				curso.setId(tempCurso.getId());
			}
		}
		
		br.close();
		
		return cursoExiste;
	}

	public void gravarCurso(Curso curso) throws IOException {
		if (!cursoExisteNoArquivo(curso)) {
			FileWriter fw = new FileWriter(ARQUIVO, true);
			fw.write("\n" + concatenarCurso(curso));
			fw.close();
			System.out.println("novo curso salvo no arquivo");
		} else {
			System.out.println("curso existe no arquivo");
		}
	}
	
	public void atualizarCurso(final Curso curso) throws IOException {
		throw new NotImplementedException();
	}
	
	public Curso removerCurso(final Curso curso) throws IOException {
		throw new NotImplementedException();
	}

	private Curso quebrarAtributos(String linha) {
		Curso curso = new Curso();
		try {
			String[] atribs = linha.split(SEPARADOR);
			curso.setId(Integer.valueOf(atribs[0]));
			curso.setSigla(atribs[1]);
			curso.setNome(atribs[2]);
			curso.setDescricao(atribs[3]);
		} catch (Exception e) {
			System.out.println("falha ao obter atributo");
		}
		
		return curso;
	}
	
	private String concatenarCurso(final Curso curso) {
		StringBuilder linha = new StringBuilder()
				.append(curso.getId()).append(SEPARADOR)
				.append(curso.getSigla()).append(SEPARADOR)
				.append(curso.getNome()).append(SEPARADOR)
				.append(curso.getDescricao().toString());
		
		return linha.toString();
	}
}
