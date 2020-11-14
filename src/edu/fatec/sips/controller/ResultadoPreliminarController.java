package edu.fatec.sips.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Curso;
import edu.fatec.sips.model.Edital;

public class ResultadoPreliminarController {
	
	

	private final ArquivoCandidatoController bdCandidato;
	private final ArquivoEditalController bdEdital;
	private final ArquivoCursoController bdCurso;
//	private final String ARQUIVO = "ResultadoPreliminar.txt";
//	private final String SEPARADOR = ";";
	private ListaLigadaSimples<Candidato> listaDeCandidatos;
	private ListaLigadaSimples<Curso> listaDeCursos;
	private ListaLigadaSimples<Edital> listaDeEditais;

	public ResultadoPreliminarController() {
		this.bdCandidato = new ArquivoCandidatoController();
		this.bdCurso = new ArquivoCursoController();
		this.bdEdital = new ArquivoEditalController();
	}
//
//	public ListaLigadaSimples<Candidato> listarCandidatos() {
//		try {
//			return this.bdCandidato.listarCandidatos();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//
//	public ListaLigadaSimples<Edital> listarEditais() {
//		try {
//			return this.bdEdital.listarEditais();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public ListaLigadaSimples<Curso> listarCursos() {
//		try {
//			return this.bdCurso.listarCursos();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	public ListaLigadaSimples<Candidato> carregarNovosDadosDeCandidadosListados() {
//		listaDeCandidatos = listarCandidatos();
//		for (int i = 0; i < listaDeCandidatos.getTamanho(); i++) {
//			inserirNotaDeCandidato(listaDeCandidatos, i);
//			inserirCriterioDeCandidato(listaDeCandidatos, i);
//		}
//		return listaDeCandidatos;
//	}
//
//	public ListaLigadaSimples<Edital> carregarDadosDeEditaisListados() {
//		listaDeEditais = listarEditais();
//		for (int i = 0; i < listaDeEditais.getTamanho(); i++) {
//			inserirNotaDeCandidato(listaDeCandidatos, i);
//			inserirCriterioDeCandidato(listaDeCandidatos, i);
//		}
//		return listaDeEditais;
//	}
//
//	public ListaLigadaSimples<Curso> carregarDadosDeCursos() {
//		return listaDeCursos = listarCursos();
//	}
//
//	public void inserirNotaDeCandidato(ListaLigadaSimples<Candidato> lista, int i) {
//		lista.espiar(i).setNota(sortearNumerosEntreIntervalos(10, 0));
//	}
//
//	public void inserirCriterioDeCandidato(ListaLigadaSimples<Candidato> lista, int i) {
//		lista.espiar(i).setCriterio(sortearNumerosEntreIntervalos(4, 1));
//	}
//
//	public int sortearNumerosEntreIntervalos(int max, int min) {
//		Random numero = new Random();
//		return numero.nextInt((max - min) + 1) + min;
//	}
//
//	public ListaLigadaSimples<Candidato> filtrarCandidatosPorCursoConformeEdital(
//			ListaLigadaSimples<Candidato> candidato, ListaLigadaSimples<Curso> curso) {
//		int idDoCurso = 1;
//		ListaLigadaSimples<Candidato> listaAux = new ListaLigadaSimples<Candidato>();
//		for (int i = 0; i < curso.getTamanho(); i++) {
//			for (int j = 0; j < candidato.getTamanho(); j++) {
//				if (curso.espiar(i).getId() == idDoCurso && candidato.espiar(j).getCurso().getId() == idDoCurso) {
//					System.out.println(candidato.espiar(j).getNome() + " " + candidato.espiar(j).getSobrenome() + " - "
//							+ curso.espiar(i).getSigla());
//					System.out.println(idDoCurso);
//					listaAux.adicionar(candidato.espiar(j));
//				}
//			}
//			idDoCurso++;
//		}
//		return listaAux;
//	}
//
//	public int definirCandidatosComMaiorNota(ListaLigadaSimples<Candidato> lista) {
//		int maiorNota = 0;
//		for (int i = 0; i < lista.getTamanho(); i++) {
//			for (int j = 0; j < i; j++) {
//				if (lista.espiar(j).getNota() > lista.espiar(i).getNota()) {
//					if (maiorNota < lista.espiar(j).getNota()) {
//						maiorNota = lista.espiar(j).getNota();
//					}
//				}
//			}
//		}
//		return maiorNota;
//	}
//
//	public void gravarResultadoPreliminar() throws IOException {
//		listaDeCursos = carregarDadosDeCursos();
//		listaDeCandidatos = filtrarCandidatosPorCursoConformeEdital(carregarNovosDadosDeCandidadosListados(),
//				listaDeCursos);
//		FileWriter fw = new FileWriter(ARQUIVO, true);
//		fw.write(retornarStringDeCandidatosQualificados(listaDeCandidatos, 7));
//		fw.close();
//	}
//
//	public void visualizarResultadoPreliminar() throws IOException {
//		String col[] = { "ID CANDIDATO", "NOME CANDIDATO", "SOBRENOME CANDIDATO", "DATA DE NASCIMENTO", "ID CURSO", "APROVADO", "NOTA CANDIDATO",
//				"CRITÉRIO" };
//		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
//		for (int i = 0; i < listaDeCandidatos.getTamanho(); i++) {
//			if (listaDeCandidatos.espiar(i).getNota() >= 7) {
//				Object[] edital = { listaDeCandidatos.espiar(i).getId(), listaDeCandidatos.espiar(i).getNome(),
//						listaDeCandidatos.espiar(i).getSobrenome(), listaDeCandidatos.espiar(i).getDataNascimento(),
//						listaDeCandidatos.espiar(i).getCurso().getId(), listaDeCandidatos.espiar(i).isAprovado(),
//						listaDeCandidatos.espiar(i).getNota(), listaDeCandidatos.espiar(i).getCriterio() };
//				tableModel.addRow(edital);
//			}
//		}
//		JTable table = new JTable(tableModel);
//		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
//		defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
//		for (int numCol = 0; numCol < table.getColumnCount(); numCol++) {
//			for (int i = 0; i <= numCol; i++) {
//				table.getColumnModel().getColumn(i).setPreferredWidth(250);
//				table.getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);
//			}
//		}
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		JOptionPane.showMessageDialog(null, new JScrollPane(table), "RESULTADO PRELIMINAR", JOptionPane.PLAIN_MESSAGE);
//	}
//
//	private String retornarStringDeCandidatosQualificados(final ListaLigadaSimples<Candidato> candidato,
//			int notaDeCorte) {
//		StringBuilder linha = new StringBuilder();
//		String ponto = ".";
//		System.out.print("Definindo resultado preliminar");
//		for (int i = 0; i < candidato.getTamanho(); i++) {
//			if (candidato.espiar(i).getNota() >= notaDeCorte) {
//				linha.append(candidato.espiar(i).getId()).append(SEPARADOR).append(candidato.espiar(i).getNome())
//						.append(SEPARADOR).append(candidato.espiar(i).getSobrenome()).append(SEPARADOR)
//						.append(candidato.espiar(i).getDataNascimento().toString()).append(SEPARADOR)
//						.append(candidato.espiar(i).getCurso().getId()).append(SEPARADOR)
//						.append(candidato.espiar(i).isAprovado()).append(SEPARADOR)
//						.append(candidato.espiar(i).getNota()).append(SEPARADOR)
//						.append(candidato.espiar(i).getCriterio()).append("\n");
//				System.out.print(ponto);
//			}
//		}
//		System.out.println("\nPronto, resultado preliminar definido, e gravado !!!");
//		return linha.toString();
//	}
//
//	public static void main(String[] args) throws IOException {
//		ResultadoPreliminarController resultadoPreliminar = new ResultadoPreliminarController();
//		resultadoPreliminar.gravarResultadoPreliminar();
//	}

}
