package edu.fatec.sips.controller;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

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

		JLabel labelCampus = new JLabel("Campus");
		String[] campus = { "Fatec - Leste", "Fatec - Sul", "Uninove - Barra Funda" };
		JComboBox<String> comboBoxCampus = new JComboBox<String>(campus);
		comboBoxCampus.setEditable(true);

		JLabel labelCurso = new JLabel("Curso");
		String[] cursos = { "ADS", "COMEX", "LOG", "RH", "POLI" };
		JComboBox<String> comboBoxCursos = new JComboBox<String>(cursos);
		comboBoxCursos.setEditable(true);

		JLabel labelPublicoAlvo = new JLabel("Público alvo");
		JTextField txtPublicoAlvo = new JTextField();

		JLabel labelPeriodoEdital = new JLabel("Período");
		JLabel labelPeriodoInicial = new JLabel("Inicial");
		JFormattedTextField txtPeriodoInicial = new JFormattedTextField(criarMascaraDeEntrada("##/##/####"));
		txtPeriodoInicial.setColumns(6);
		txtPeriodoInicial.setHorizontalAlignment(JTextField.CENTER);
		JLabel labelIconePeriodoInicial = new JLabel(new ImageIcon("icon-calendar.png"));
		JLabel labelPeriodoFinal = new JLabel("Final");
		JFormattedTextField txtPeriodoFinal = new JFormattedTextField(criarMascaraDeEntrada("##/##/####"));
		txtPeriodoFinal.setColumns(6);
		txtPeriodoFinal.setHorizontalAlignment(JTextField.CENTER);
		JLabel labelIconePeriodoFinal = new JLabel(new ImageIcon("icon-calendar.png"));

		JLabel labelQuantidadeVagas = new JLabel("Quantidade de vagas");
		JTextField txtQtdVagasAmplaConcorrencia = new JTextField(3);
		JTextField txtQtdVagasAcoesAfirmativas = new JTextField(3);
		JTextField txtQtdVagasDeficiente = new JTextField(3);

		JLabel labelCriterio = new JLabel("Critério");
		String[] criterio = { "1", "2", "3" };
		JComboBox<String> comboBoxCriterio = new JComboBox<String>(criterio);
		comboBoxCursos.setEditable(true);

		JPanel periodoInicial = new JPanel();
		periodoInicial.add(labelPeriodoInicial);
		periodoInicial.add(txtPeriodoInicial);
		periodoInicial.add(labelIconePeriodoInicial);

		JPanel periodoFinal = new JPanel();
		periodoFinal.add(labelPeriodoFinal);
		periodoFinal.add(txtPeriodoFinal);
		periodoFinal.add(labelIconePeriodoFinal);

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

		Object[] options = { labelTitulo, txtTitulo, labelCampus, comboBoxCampus, labelCurso, comboBoxCursos,
				labelPublicoAlvo, txtPublicoAlvo, labelPeriodoEdital, periodoEdital, labelQuantidadeVagas,
				quantitativos, labelCriterio, comboBoxCriterio };
		JOptionPane.showMessageDialog(null, options, "CADASTRAR EDITAL", JOptionPane.PLAIN_MESSAGE);

		edital.setTitulo(txtTitulo.getText());
		edital.setCampus(comboBoxCampus.getSelectedItem().toString());
		edital.setCurso(comboBoxCursos.getSelectedItem().toString());
		edital.setPublicoAlvo(txtPublicoAlvo.getText());
		edital.setPeriodoInicial(txtPeriodoInicial.getText());
		edital.setPeriodoFinal(txtPeriodoFinal.getText());
		edital.setAmplaConcorrencia(Integer.parseInt(txtQtdVagasAmplaConcorrencia.getText()));
		edital.setAcoesAfirmativas(Integer.parseInt(txtQtdVagasAcoesAfirmativas.getText()));
		edital.setAcoesAfirmativas(Integer.parseInt(txtQtdVagasDeficiente.getText()));
		edital.setCriterio(Integer.parseInt(comboBoxCriterio.getSelectedItem().toString()));

		bdEdital.gravarEdital(edital);

	}

	public void visualizarEdital() {
		String col[] = { "ID", "TÍTULO", "CAMPUS","CURSO", "PÚB. ALVO", "P. INICIAL", "P. FINAL", "QTD. VAGAS A.C.", "QTD. VAGAS A.A.",
				"QTD. VAGAS D.","CRITÉRIO" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < listaDeEdital.getTamanho(); i++) {
			Object[] edital = { listaDeEdital.espiar(i).getId(), listaDeEdital.espiar(i).getTitulo(),
					listaDeEdital.espiar(i).getCurso(), listaDeEdital.espiar(i).getPublicoAlvo(),
					listaDeEdital.espiar(i).getPeriodoInicial(), listaDeEdital.espiar(i).getPeriodoFinal(),
					listaDeEdital.espiar(i).getAmplaConcorrencia(), listaDeEdital.espiar(i).getAcoesAfirmativas(),
					listaDeEdital.espiar(i).getDeficiente(), listaDeEdital.espiar(i).getCriterio() };
			tableModel.addRow(edital);
		}
		JTable table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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

	public MaskFormatter criarMascaraDeEntrada(String formatoDeMascara) {

		MaskFormatter F_Mascara = new MaskFormatter();
		try {
			F_Mascara.setMask(formatoDeMascara); // Atribui a mascara
			F_Mascara.setPlaceholderCharacter(' '); // Caracter para preencimento
		} catch (Exception excecao) {
			excecao.printStackTrace();
		}
		return F_Mascara;
	}
}
