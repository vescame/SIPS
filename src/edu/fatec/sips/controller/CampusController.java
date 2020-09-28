package edu.fatec.sips.controller;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.fatec.sips.view.candidato.ListarCandidatos;

public class CampusController {
	ListarCandidatos listarCandidatos = new ListarCandidatos();

	public void visualizarCandidatos() {
		listarCandidatos.listar();
	}

	public void cadastrarEdital() {
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

		System.out.println("Título do edital: " + txtTitulo.getText() + "\n" + "Curso: "
				+ comboBoxCursos.getSelectedItem().toString() + "\n" + "Público alvo: " + txtPublicoAlvo.getText()
				+ "\n" + "Período inicial: " + txtPeriodoInicial.getText() + "\n" + "Período final: "
				+ txtPeriodoFinal.getText() + "\n" + "Quantidade de vagas: " + txtQuantidadeDeVagas.getText() + "\n"
				+ "Tipo de vaga: " + txtTipoDeVaga.getText() + "\n" + "Critério: " + txtCriterio.getText().toString());
	}
}
