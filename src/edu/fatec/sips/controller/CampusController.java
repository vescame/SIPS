package edu.fatec.sips.controller;

import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.dao.DAOEDITAL;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Edital;
import edu.fatec.sips.view.candidato.ListarCandidatos;

public class CampusController {
	private final ArquivoEditalController bdEdital;
	
	ListaLigadaSimples<Edital> listaDeEdital = new ListaLigadaSimples<Edital>();
	Edital edital = new Edital();
	DAOEDITAL daoedital = new DAOEDITAL();
	
	public CampusController() {
		this.bdEdital = new ArquivoEditalController();
	}	
	
	ListarCandidatos listarCandidatos = new ListarCandidatos();	

	public void visualizarCandidatos() {
		listarCandidatos.listar();
	}
	
	public ListaLigadaSimples<Edital> listarEditais() {
		try {
			return this.bdEdital.listarEditais();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void cadastrarEdital() throws IOException {
		JLabel labelTitulo = new JLabel("Título do edital");
		JTextField txtTitulo = new JTextField();

		JLabel labelCurso = new JLabel("Curso");
		String[] cursos = { "ADS", "COMEX", "LOG", "RH", "POLI" };
		JComboBox<String> comboBoxCursos = new JComboBox<String>(cursos);
		comboBoxCursos.setEditable(true);

		JLabel labelPublicoAlvo = new JLabel("Público alvo");
		JTextField txtPublicoAlvo = new JTextField();

		JLabel labelPeriodoInicial = new JLabel("Período inicial");
		JTextField txtPeriodoInicial = new JTextField();

		JLabel labelPeriodoFinal = new JLabel("Período final");
		JTextField txtPeriodoFinal = new JTextField();

		JLabel labelQuantidadeVagas = new JLabel("Quantidade de vagas");
		JTextField txtQuantidadeDeVagas = new JTextField();

		JLabel labelTipoDeVaga = new JLabel("Tipo de vaga");
		JTextField txtTipoDeVaga = new JTextField();

		JLabel labelCriterio = new JLabel("Critério");
		JTextField txtCriterio = new JTextField();

		Object[] options = { labelTitulo, txtTitulo, labelCurso, comboBoxCursos, labelPublicoAlvo, txtPublicoAlvo,
				labelPeriodoInicial, txtPeriodoInicial, labelPeriodoFinal, txtPeriodoFinal, labelQuantidadeVagas,
				txtQuantidadeDeVagas, labelTipoDeVaga, txtTipoDeVaga, labelCriterio, txtCriterio };
		JOptionPane.showMessageDialog(null, options, "CADASTRAR EDITAL", JOptionPane.PLAIN_MESSAGE);
		
		edital.setTitulo(txtTitulo.getText());
		edital.setCurso(comboBoxCursos.getSelectedItem().toString());
		edital.setPublicoAlvo(txtPublicoAlvo.getText());
		edital.setPeriodoInicial(txtPeriodoInicial.getText());
		edital.setPeriodoFinal(txtPeriodoFinal.getText());
		edital.setQtdVagas(Integer.parseInt(txtQuantidadeDeVagas.getText()));
		edital.setTipoVaga(txtTipoDeVaga.getText());
		edital.setCriterio(Integer.parseInt(txtCriterio.getText()));
		
		bdEdital.gravarEdital(edital);
		
//		System.out.println("Título do edital: " + txtTitulo.getText() + "\n" + "Curso: "
//				+ comboBoxCursos.getSelectedItem().toString() + "\n" + "Público alvo: " + txtPublicoAlvo.getText()
//				+ "\n" + "Período inicial: " + txtPeriodoInicial.getText() + "\n" + "Período final: "
//				+ txtPeriodoFinal.getText() + "\n" + "Quantidade de vagas: " + txtQuantidadeDeVagas.getText() + "\n"
//				+ "Tipo de vaga: " + txtTipoDeVaga.getText() + "\n" + "Critério: " + txtCriterio.getText().toString());
	
	}
	
	public void visualizarEdital() {
		String col[] = { "ID", "TITULO", "CURSO", "PUBLICO ALVO", "PERIODO INICIAL", "PERIODO FINAL", "QTD VAGAS",
				"TIPO VAGA", "CRITERIO"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < listaDeEdital.getTamanho(); i++) {
			Object[] edital = { listaDeEdital.espiar(i).getId(), listaDeEdital.espiar(i).getTitulo(),
					listaDeEdital.espiar(i).getCurso(), listaDeEdital.espiar(i).getPublicoAlvo(), 
					listaDeEdital.espiar(i).getPeriodoInicial(), listaDeEdital.espiar(i).getPeriodoFinal(), 
					listaDeEdital.espiar(i).getQtdVagas(), listaDeEdital.espiar(i).getTipoVaga(),
					listaDeEdital.espiar(i).getCriterio()};
			tableModel.addRow(edital);
		}
		JTable table = new JTable(tableModel);
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "LISTA DE EDITAL", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void salvar(final Edital edital) {
		try {
			this.bdEdital.gravarEdital(edital);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizar(final Edital edital) {
		try {
			this.bdEdital.atualizarEdital(edital);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Edital remover(final Edital edital) {
		Edital removido = null;
		
		try {
			removido = this.bdEdital.removerEdital(edital);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (removido == null) {
				System.out.println("edital de id " + edital.getId() + " nao existe");
			}
		}
		
		return removido;
	}
}
