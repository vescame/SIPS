package edu.fatec.sips.view;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import edu.fatec.sips.controller.CampusController;
import edu.fatec.sips.controller.EditalController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Campus;
import edu.fatec.sips.model.Curso;
import edu.fatec.sips.model.Edital;

public class EditarEdital {
	private final JTable tabelaDeEditais;
	private final DefaultTableModel modeloTabela;
	private final String colunas[] = { "ID", "TÍTULO", "CAMPUS", "CURSO", "PÚBLICO ALVO", "PERÍODO INICIAL",
			"PERÍODO FINAL", "QTD. VAGAS AMPLA CONCORRÊNCIA", "QTD. VAGAS AÇÕES AFIRMATIVAS", "QTD. VAGAS DEFICIENTE",
			"CRITÉRIO" };
	private final EditalController editalController = new EditalController();
	private final ListaLigadaSimples<Edital> editais;
	private CampusController campusController = new CampusController();

	public EditarEdital() {
		this.modeloTabela = new DefaultTableModel(colunas, 0);
		this.tabelaDeEditais = new JTable(modeloTabela);

		editais = editalController.listarEditais();

		try {
			for (int i = 0; i < editais.getTamanho(); ++i) {
				Edital editalModel = editais.espiar(i);
				Object[] editalOject = { editalModel.getId(), editalModel.getTitulo(), editalModel.getCampus(),
						editalModel.getCurso(), editalModel.getPublicoAlvo(), editalModel.getPeriodoInicial(),
						editalModel.getPeriodoFinal(), editalModel.getAmplaConcorrencia(),
						editalModel.getAcoesAfirmativas(), editalModel.getDeficiente(), editalModel.getCriterio() };
				modeloTabela.addRow(editalOject);
			}
		} catch (NullPointerException e) {
			System.out.println("FALHA AO ADICIONAR CANDIDATO A LISTA");
		}
	}

	public void editarEdital() {
		tabelaDeEditais.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evento) {
				try {
					selecaoDeLinha(evento);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
		defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int numCol = 0; numCol < tabelaDeEditais.getColumnCount(); numCol++) {
			for (int i = 0; i <= numCol; i++) {
				tabelaDeEditais.getColumnModel().getColumn(i).setPreferredWidth(250);
				tabelaDeEditais.getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);
			}
		}
		tabelaDeEditais.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JOptionPane.showMessageDialog(null, new JScrollPane(tabelaDeEditais), "LISTA DE EDITAIS",
				JOptionPane.PLAIN_MESSAGE);
	}

	private void selecaoDeLinha(MouseEvent evt) throws Exception {
		final int colunaId = 0;
		int indexLinhaSelecionada = tabelaDeEditais.getSelectedRow();
		int idSelecionado = (int) this.modeloTabela.getValueAt(indexLinhaSelecionada, colunaId);
		Edital editalSelecionado = this.editalController.getPorId(idSelecionado);
		mostrarTela(editalSelecionado);
	}

	public void mostrarTela(Edital editalSelecionado) throws Exception {
		JTextField txtTitulo = getTitulo(editalSelecionado);
		JTextField txtPublicoAlvo = getPublicoAlvo(editalSelecionado);
		JTextField txtPeriodoInicial = criarFormatterTxtFieldData(getPeriodoInicial(editalSelecionado));
		JTextField txtPeriodoFinal = criarFormatterTxtFieldData(getPeriodoFinal(editalSelecionado));
		JSpinner spinnerQtdeVagasAmplaConcorrencia = criarSpinner(0, 0, 100, 1);
		JComboBox<Campus> comboBoxCampus = carregarComboBoxCampus(editalSelecionado);
		JComboBox<Curso> comboBoxCursos = carregarComboBoxCurso(editalSelecionado);
		JComboBox<String> comboBoxCriterio = carregarComboCriterio(editalSelecionado);
		JSpinner spinnerQtdeVagasAcoesAfirmativas = criarSpinner(0, 0, 100, 1);
		JSpinner spinnerQtdeVagasDeficiente = criarSpinner(0, 0, 100, 1);
		JFormattedTextField txtQtdVagasAmplaConcorrencia = criarFormatterTxtFieldNumber(
				spinnerQtdeVagasAmplaConcorrencia, getQtdVagasAmplaConcorrencia(editalSelecionado));
		JFormattedTextField txtQtdVagasAcoesAfirmativas = criarFormatterTxtFieldNumber(spinnerQtdeVagasAcoesAfirmativas,
				getQtdeVagasAcoesAfirmativas(editalSelecionado));
		JFormattedTextField txtQtdVagasDeficiente = criarFormatterTxtFieldNumber(spinnerQtdeVagasDeficiente,
				getQtdeVagasDeficiente(editalSelecionado));
		int cadastrarEdital = JOptionPane.showConfirmDialog(null,
				montarTela(comboBoxCursos, txtTitulo, comboBoxCampus, txtPublicoAlvo, txtPeriodoInicial,
						txtPeriodoFinal, spinnerQtdeVagasAmplaConcorrencia, spinnerQtdeVagasAcoesAfirmativas,
						spinnerQtdeVagasDeficiente, comboBoxCriterio),
				"EDITAR EDITAL", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE);
		validarEdicaoDeEdital(cadastrarEdital, comboBoxCursos, comboBoxCampus, txtTitulo, txtPublicoAlvo,
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

	public JFormattedTextField criarFormatterTxtFieldNumber(JSpinner spinner, int valorInicial) {
		JFormattedTextField txtQtdVagasAmplaConcorrencia = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
		((NumberFormatter) txtQtdVagasAmplaConcorrencia.getFormatter()).setAllowsInvalid(false);
		txtQtdVagasAmplaConcorrencia.setText(String.valueOf(valorInicial));
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

	public JComboBox<Campus> carregarComboBoxCampus(Edital editalSelecionado) throws IOException {
		ListaLigadaSimples<Campus> listaDeCampus = campusController.retornarListaDeCampus();
		JComboBox<Campus> campus = new JComboBox<Campus>();
		for (int i = 0; i < listaDeCampus.getTamanho(); i++) {
			campus.addItem(listaDeCampus.espiar(i));
			if (editalSelecionado.getCampus().getId() == listaDeCampus.espiar(i).getId()) {
				campus.setSelectedIndex(i);
			}
		}
		campus.setEditable(false);
		return campus;
	}

	public JComboBox<Curso> carregarComboBoxCurso(Edital editalSelecionado) throws IOException {
		ListaLigadaSimples<Curso> listaDeCurso = campusController.retornarListaDeCurso();
		JComboBox<Curso> cursos = new JComboBox<Curso>();
		for (int i = 0; i < listaDeCurso.getTamanho(); i++) {
			cursos.addItem(listaDeCurso.espiar(i));
			if (editalSelecionado.getCurso().getId() == listaDeCurso.espiar(i).getId()) {
				cursos.setSelectedIndex(i);
			}
		}
		cursos.setEditable(false);
		return cursos;
	}

	public JComboBox<String> carregarComboCriterio(Edital editalSelecionado) {
		ListaLigadaSimples<String> listaDeCriterio = new ListaLigadaSimples<String>();
		listaDeCriterio.adicionar("1");
		listaDeCriterio.adicionar("2");
		listaDeCriterio.adicionar("3");
		JComboBox<String> criterios = new JComboBox<String>();
		for (int i = 0; i < listaDeCriterio.getTamanho(); i++) {
			criterios.addItem(listaDeCriterio.espiar(i));
			if (editalSelecionado.getCriterio() == Integer.valueOf(listaDeCriterio.espiar(i))) {
				criterios.setSelectedIndex(i);
			}
		}
		criterios.setEditable(false);
		return criterios;
	}

	public void validarEdicaoDeEdital(int cadastrarEdital, JComboBox<Curso> comboBoxCursos,
			JComboBox<Campus> comboBoxCampus, JTextField txtTitulo, JTextField txtPublicoAlvo,
			JTextField txtPeriodoInicial, JTextField txtPeriodoFinal, JFormattedTextField txtQtdVagasAmplaConcorrencia,
			JFormattedTextField txtQtdVagasAcoesAfirmativas, JFormattedTextField txtQtdVagasDeficiente,
			JComboBox<String> comboBoxCriterio) throws Exception {
		if (cadastrarEdital == JOptionPane.OK_OPTION) {
			Curso curso = (Curso) comboBoxCursos.getModel().getSelectedItem();
			Campus campus = (Campus) comboBoxCampus.getModel().getSelectedItem();
			campusController.atualizarEdital(txtTitulo, campus, curso, txtPublicoAlvo, txtPeriodoInicial,
					txtPeriodoFinal, txtQtdVagasAmplaConcorrencia, txtQtdVagasAcoesAfirmativas, txtQtdVagasDeficiente,
					comboBoxCriterio);
		}
	}

	private JTextField getPublicoAlvo(Edital editalSelecionado) {
		return new JTextField(editalSelecionado.getPublicoAlvo());
	}

	private JTextField getTitulo(Edital editalSelecionado) {
		return new JTextField(editalSelecionado.getTitulo());
	}

	private String getPeriodoInicial(Edital editalSelecionado) {
		return editalSelecionado.getPeriodoInicial();
	}

	private String getPeriodoFinal(Edital editalSelecionado) {
		return editalSelecionado.getPeriodoFinal();
	}

	private int getQtdVagasAmplaConcorrencia(Edital editalSelecionado) {
		return editalSelecionado.getAmplaConcorrencia();
	}

	private int getQtdeVagasAcoesAfirmativas(Edital editalSelecionado) {
		return editalSelecionado.getAcoesAfirmativas();
	}

	private int getQtdeVagasDeficiente(Edital editalSelecionado) {
		return editalSelecionado.getDeficiente();
	}
}
