package edu.fatec.sips.controller;

import java.awt.GradientPaint;
import java.awt.GridBagLayout;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

		JLabel labelPeriodoEdital = new JLabel("Período");
		JLabel labelPeriodoInicial = new JLabel("Inicial");
		JTextField txtPeriodoInicial = new JTextField(11);
		JLabel labelPeriodoFinal = new JLabel("Final");
		JTextField txtPeriodoFinal = new JTextField(11);

		JLabel labelQuantidadeVagas = new JLabel("Quantidade de vagas");
		JTextField txtQtdVagasAmplaConcorrencia = new JTextField(5);
		JTextField txtQtdVagasAcoesAfirmativas = new JTextField(5);
		JTextField txtQtdVagasDeficiente = new JTextField(5);

		JLabel labelCriterio = new JLabel("Critério");
		String[] criterio = { "1", "2", "3"};
		JComboBox<String> comboBoxCriterio = new JComboBox<String>(criterio);
		comboBoxCursos.setEditable(true);

		JPanel periodoInicial = new JPanel();
		periodoInicial.add(labelPeriodoInicial);
		periodoInicial.add(txtPeriodoInicial);

		JPanel periodoFinal = new JPanel();
		periodoFinal.add(labelPeriodoFinal);
		periodoFinal.add(txtPeriodoFinal);

		JPanel periodoEdital = new JPanel();
		periodoEdital.add(periodoInicial);
		periodoEdital.add(periodoFinal);

		JPanel qtdVagasAmplaConcorrencia = new JPanel();
		qtdVagasAmplaConcorrencia.add(new JLabel("Ampla concorrência"));
		qtdVagasAmplaConcorrencia.add(txtQtdVagasAmplaConcorrencia);

		JPanel qtdVagasAcoesAfirmativas = new JPanel();
		qtdVagasAcoesAfirmativas.add(new JLabel("Ações afirmativas"));
		qtdVagasAcoesAfirmativas.add(txtQtdVagasAcoesAfirmativas);

		JPanel qtdVagasDeficiente = new JPanel();
		qtdVagasDeficiente.add(new JLabel("Deficiente"));
		qtdVagasDeficiente.add(txtQtdVagasDeficiente);

		JPanel quantitativos = new JPanel();
		quantitativos.add(qtdVagasAmplaConcorrencia);
		quantitativos.add(qtdVagasAcoesAfirmativas);
		quantitativos.add(qtdVagasDeficiente);

		Object[] options = { labelTitulo, txtTitulo, labelCurso, comboBoxCursos, labelPublicoAlvo, txtPublicoAlvo,
				labelPeriodoEdital, periodoEdital, labelQuantidadeVagas, quantitativos, labelCriterio, comboBoxCriterio };
		JOptionPane.showMessageDialog(null, options, "CADASTRAR EDITAL", JOptionPane.PLAIN_MESSAGE);

		edital.setTitulo(txtTitulo.getText());
		edital.setCurso(comboBoxCursos.getSelectedItem().toString());
		edital.setPublicoAlvo(txtPublicoAlvo.getText());
		edital.setPeriodoInicial(txtPeriodoInicial.getText());
		edital.setPeriodoFinal(txtPeriodoFinal.getText());
//		edital.setQtdVagas(Integer.parseInt(txtQuantidadeDeVagas.getText()));
//		edital.setTipoVaga(txtTipoDeVaga.getText());
		edital.setCriterio(Integer.parseInt(comboBoxCriterio.getSelectedItem().toString()));

		bdEdital.gravarEdital(edital);

//		System.out.println("Título do edital: " + txtTitulo.getText() + "\n" + "Curso: "
//				+ comboBoxCursos.getSelectedItem().toString() + "\n" + "Público alvo: " + txtPublicoAlvo.getText()
//				+ "\n" + "Período inicial: " + txtPeriodoInicial.getText() + "\n" + "Período final: "
//				+ txtPeriodoFinal.getText() + "\n" + "Quantidade de vagas: " + txtQuantidadeDeVagas.getText() + "\n"
//				+ "Tipo de vaga: " + txtTipoDeVaga.getText() + "\n" + "Critério: " + txtCriterio.getText().toString());

	}

	public void visualizarEdital() {
		String col[] = { "ID", "TITULO", "CURSO", "PUBLICO ALVO", "PERIODO INICIAL", "PERIODO FINAL", "QTD VAGAS",
				"TIPO VAGA", "CRITERIO" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < listaDeEdital.getTamanho(); i++) {
			Object[] edital = { listaDeEdital.espiar(i).getId(), listaDeEdital.espiar(i).getTitulo(),
					listaDeEdital.espiar(i).getCurso(), listaDeEdital.espiar(i).getPublicoAlvo(),
					listaDeEdital.espiar(i).getPeriodoInicial(), listaDeEdital.espiar(i).getPeriodoFinal(),
					listaDeEdital.espiar(i).getQtdVagas(), listaDeEdital.espiar(i).getTipoVaga(),
					listaDeEdital.espiar(i).getCriterio() };
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
