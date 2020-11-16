package edu.fatec.sips.view;

import java.awt.Component;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

import edu.fatec.sips.controller.CampusController;
import edu.fatec.sips.data_structure.FilaImplementacaoDinamica;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.file_controller.ArquivoCronogramaController;
import edu.fatec.sips.model.Campus;
import edu.fatec.sips.model.CronogramaDeAtividades;
import edu.fatec.sips.model.Curso;

public class CadastrarEdital {
	CampusController campusController = new CampusController();

	public void cadastrarEdital() throws IOException {
		JTextField txtTitulo = criarTxtField();
		JTextField txtPublicoAlvo = criarTxtField();
		JTextField txtPeriodoInicial = criarFormatterTxtFieldData(carregarDataInicial());
		JTextField txtPeriodoFinal = criarFormatterTxtFieldData(carregarDataFinal());
		JSpinner spinnerQtdeVagasAmplaConcorrencia = criarSpinner(0, 0, 100, 1);
		JComboBox<Campus> comboBoxCampus = carregarComboBoxCampus();
		JComboBox<Curso> comboBoxCursos = carregarComboBoxCurso();
		JComboBox<String> comboBoxCriterio = carregarComboCriterio();
		JSpinner spinnerQtdeVagasAcoesAfirmativas = criarSpinner(0, 0, 100, 1);
		JSpinner spinnerQtdeVagasDeficiente = criarSpinner(0, 0, 100, 1);
		JFormattedTextField txtQtdVagasAmplaConcorrencia = criarFormatterTxtFieldNumber(
				spinnerQtdeVagasAmplaConcorrencia);
		JFormattedTextField txtQtdVagasAcoesAfirmativas = criarFormatterTxtFieldNumber(
				spinnerQtdeVagasAcoesAfirmativas);
		JFormattedTextField txtQtdVagasDeficiente = criarFormatterTxtFieldNumber(spinnerQtdeVagasDeficiente);
		int cadastrarEdital = JOptionPane.showConfirmDialog(null,
				montarTela(comboBoxCursos, txtTitulo, comboBoxCampus, txtPublicoAlvo, txtPeriodoInicial,
						txtPeriodoFinal, spinnerQtdeVagasAmplaConcorrencia, spinnerQtdeVagasAcoesAfirmativas,
						spinnerQtdeVagasDeficiente, comboBoxCriterio),
				"CADASTRAR EDITAL", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
		validarSalvamentoDeEdital(cadastrarEdital, comboBoxCursos, comboBoxCampus, txtTitulo, txtPublicoAlvo,
				txtPeriodoInicial, txtPeriodoFinal, txtQtdVagasAmplaConcorrencia, txtQtdVagasAcoesAfirmativas,
				txtQtdVagasDeficiente, comboBoxCriterio);
	}

	public JLabel criarLabel(String texto) {
		return new JLabel(texto);
	}

	public JLabel criarLabelIcon(String caminhoDaImagem) {
		return new JLabel(new ImageIcon(caminhoDaImagem));
	}

	public JTextField criarTxtField() {
		return new JTextField();
	}

	public JFormattedTextField criarFormatterTxtFieldNumber(JSpinner spinner) {
		JFormattedTextField txtQtdVagasAmplaConcorrencia = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
		((NumberFormatter) txtQtdVagasAmplaConcorrencia.getFormatter()).setAllowsInvalid(false);
		return txtQtdVagasAmplaConcorrencia;
	}

	public JFormattedTextField criarFormatterTxtFieldData(String data) {
		JFormattedTextField txtData = new JFormattedTextField(data);
		txtData.setColumns(6);
		txtData.setHorizontalAlignment(JTextField.CENTER);
		txtData.setEditable(false);
		return txtData;
	}

	public JSpinner criarSpinner(int a, int b, int c, int d) {
		return new JSpinner(new SpinnerNumberModel(a, b, c, d));
	}

	public JPanel criarPanelDePeriodo(Component txtPeriodoInicial, Component txtPeriodoFinal) {
		JPanel periodoInicial = new JPanel();
		periodoInicial.add(criarLabel("Período Inicial"));
		periodoInicial.add(txtPeriodoInicial);
		periodoInicial.add(criarLabelIcon("icon-calendar.png"));

		JPanel periodoFinal = new JPanel();
		periodoFinal.add(criarLabel("Período Final"));
		periodoFinal.add(txtPeriodoFinal);
		periodoFinal.add(criarLabelIcon("icon-calendar.png"));

		JPanel periodoEdital = new JPanel();
		periodoEdital.add(periodoInicial);
		periodoEdital.add(periodoFinal);

		return periodoEdital;
	}

	public JPanel criarPanelQuantitativo(Component spinnerQtdeVagasAmplaConcorrencia,
			Component spinnerQtdeVagasAcoesAfirmativas, Component spinnerQtdeVagasDeficiente) {
		JPanel qtdVagasAmplaConcorrencia = new JPanel();
		qtdVagasAmplaConcorrencia.add(criarLabel("Ampla concorrência"));
		qtdVagasAmplaConcorrencia.add(spinnerQtdeVagasAmplaConcorrencia);

		JPanel qtdVagasAcoesAfirmativas = new JPanel();
		qtdVagasAcoesAfirmativas.add(criarLabel("Ações afirmativas"));
		qtdVagasAcoesAfirmativas.add(spinnerQtdeVagasAcoesAfirmativas);

		JPanel qtdVagasDeficiente = new JPanel();
		qtdVagasDeficiente.add(criarLabel("Deficiente"));
		qtdVagasDeficiente.add(spinnerQtdeVagasDeficiente);

		JPanel quantitativos = new JPanel();
		quantitativos.add(qtdVagasAmplaConcorrencia);
		quantitativos.add(qtdVagasAcoesAfirmativas);
		quantitativos.add(qtdVagasDeficiente);

		return quantitativos;
	}

	public Object montarTela(JComboBox<Curso> comboBoxCursos, JTextField txtTitulo, JComboBox<Campus> comboBoxCampus,
			JTextField txtPublicoAlvo, JTextField txtPeriodoInicial, JTextField txtPeriodoFinal,
			JSpinner spinnerQtdeVagasAmplaConcorrencia, JSpinner spinnerQtdeVagasAcoesAfirmativas,
			JSpinner spinnerQtdeVagasDeficiente, JComboBox<String> comboBoxCriterio) {
		Object[] componentesTela = { criarLabel("Título do Edital"), txtTitulo, criarLabel("Campus"), comboBoxCampus,
				criarLabel("Curso"), comboBoxCursos, criarLabel("Público Alvo"), txtPublicoAlvo, criarLabel("Período"),
				criarPanelDePeriodo(txtPeriodoInicial, txtPeriodoFinal), criarLabel("Quantitativos"),
				criarPanelQuantitativo(spinnerQtdeVagasAmplaConcorrencia, spinnerQtdeVagasAcoesAfirmativas,
						spinnerQtdeVagasDeficiente),
				criarLabel("Critério"), comboBoxCriterio };
		return componentesTela;
	}

	public JComboBox<Campus> carregarComboBoxCampus() throws IOException {
		ListaLigadaSimples<Campus> listaDeCampus = campusController.retornarListaDeCampus();
		JComboBox<Campus> campus = new JComboBox<Campus>();
		for (int i = 0; i < listaDeCampus.getTamanho(); i++) {
			campus.addItem(listaDeCampus.espiar(i));
		}
		campus.setEditable(false);
		return campus;
	}

	public JComboBox<Curso> carregarComboBoxCurso() throws IOException {
		ListaLigadaSimples<Curso> listaDeCurso = campusController.retornarListaDeCurso();
		JComboBox<Curso> cursos = new JComboBox<Curso>();
		for (int i = 0; i < listaDeCurso.getTamanho(); i++) {
			cursos.addItem(listaDeCurso.espiar(i));
		}
		cursos.setEditable(false);
		return cursos;
	}

	public JComboBox<String> carregarComboCriterio() {
		ListaLigadaSimples<String> listaDeCriterio = new ListaLigadaSimples<String>();
		listaDeCriterio.adicionar("1");
		listaDeCriterio.adicionar("2");
		listaDeCriterio.adicionar("3");
		JComboBox<String> criterios = new JComboBox<String>();
		for (int i = 0; i < listaDeCriterio.getTamanho(); i++) {
			criterios.addItem(listaDeCriterio.espiar(i));
		}
		criterios.setEditable(false);
		return criterios;
	}

	public String carregarDataInicial() throws IOException {
		ArquivoCronogramaController arquivoCronogramaController = new ArquivoCronogramaController();
		FilaImplementacaoDinamica<CronogramaDeAtividades> filaImplementacaoDinamica = arquivoCronogramaController
				.listarAtividades();
		System.out.println(filaImplementacaoDinamica.coletar(0).getDataInicio());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return String.valueOf(sdf.format(filaImplementacaoDinamica.coletar(0).getDataInicio()));
	}

	public String carregarDataFinal() throws IOException {
		ArquivoCronogramaController arquivoCronogramaController = new ArquivoCronogramaController();
		FilaImplementacaoDinamica<CronogramaDeAtividades> filaImplementacaoDinamica = arquivoCronogramaController
				.listarAtividades();
		System.out.println(
				filaImplementacaoDinamica.coletar(arquivoCronogramaController.ultimoId() - 2).getDataEntrega());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return String.valueOf(sdf.format(
				filaImplementacaoDinamica.coletar(arquivoCronogramaController.ultimoId() - 2).getDataEntrega()));
	}

	public void validarSalvamentoDeEdital(int cadastrarEdital, JComboBox<Curso> comboBoxCursos,
			JComboBox<Campus> comboBoxCampus, JTextField txtTitulo, JTextField txtPublicoAlvo,
			JTextField txtPeriodoInicial, JTextField txtPeriodoFinal, JFormattedTextField txtQtdVagasAmplaConcorrencia,
			JFormattedTextField txtQtdVagasAcoesAfirmativas, JFormattedTextField txtQtdVagasDeficiente,
			JComboBox<String> comboBoxCriterio) throws IOException {
		if (cadastrarEdital == JOptionPane.OK_OPTION) {
			Curso curso = (Curso) comboBoxCursos.getModel().getSelectedItem();
			Campus campus = (Campus) comboBoxCampus.getModel().getSelectedItem();
			campusController.salvarEdital(txtTitulo, campus, curso, txtPublicoAlvo, txtPeriodoInicial, txtPeriodoFinal,
					txtQtdVagasAmplaConcorrencia, txtQtdVagasAcoesAfirmativas, txtQtdVagasDeficiente, comboBoxCriterio);
		}
	}
}
