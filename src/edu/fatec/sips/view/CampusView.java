package edu.fatec.sips.view;

import java.awt.HeadlessException;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import edu.fatec.sips.controller.CampusController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Edital;

public class CampusView {

	CampusController campusController = new CampusController();

	public int menuCampus(int opcao) throws NumberFormatException, HeadlessException, IOException {
		if (opcao != 99) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("MENU CAMPUS \n\n 1- Cadastrar edital \n"
					+ " 2 - Editar edital \n" + " 3- Visualizar edital \n 4- Visualizar candidatos \n"
					+ " 5- Visualizar recursos  \n" + " 6- Definir resultado preliminar \n"
					+ " 7- Visualizar resultado preliminar \n" + " 8- Editar resultados finais \n"
					+ " 9 - Visualizar resultado final \n" + "10 - Visualizar datas de entrevistas \n"
					+ " 11 - Visualizar cronograma de entrevistas \n" + " 99- Sair"));
			avaliarOpcao(opcao);
			return menuCampus(opcao);
		} else {
			return opcao;
		}
	}

	public void avaliarOpcao(int opcao) throws NumberFormatException, HeadlessException, IOException {
		switch (opcao) {
		case 1:
			cadastrarEdital();
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 3:
			visualizarEdital();
			break;
		case 4:
			visualizarCandidatos();
			break;
		case 5:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 6:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 7:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 8:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 9:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 10:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 11:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 99:
			JOptionPane.showMessageDialog(null, "Encerrando sessão...");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Opção Inválida !!!");
		}
	}

	public void cadastrarEdital() throws IOException {
		JLabel labelTitulo = new JLabel("Título do edital");
		JTextField txtTitulo = new JTextField();

		JLabel labelCampus = new JLabel("Campus");
		String[] campus = { "Fatec - Leste", "Fatec - Sul", "Uninove - Barra Funda" };
		JComboBox<String> comboBoxCampus = new JComboBox<String>(campus);
		comboBoxCampus.setEditable(false);

		JLabel labelCurso = new JLabel("Curso");
		String[] cursos = { "ADS", "COMEX", "LOG", "RH", "POLI" };
		JComboBox<String> comboBoxCursos = new JComboBox<String>(cursos);
		comboBoxCursos.setEditable(false);

		JLabel labelPublicoAlvo = new JLabel("Público alvo");
		JTextField txtPublicoAlvo = new JTextField();

		JLabel labelPeriodoEdital = new JLabel("Período");
		JLabel labelPeriodoInicial = new JLabel("Inicial");
		JFormattedTextField txtPeriodoInicial = new JFormattedTextField(criarMascaraDeEntrada("##/##/####"));
		txtPeriodoInicial.setColumns(6);
		txtPeriodoInicial.setHorizontalAlignment(JTextField.CENTER);
		txtPeriodoInicial.setEditable(false);
		JLabel labelIconePeriodoInicial = new JLabel(new ImageIcon("icon-calendar.png"));
		JLabel labelPeriodoFinal = new JLabel("Final");
		JFormattedTextField txtPeriodoFinal = new JFormattedTextField(criarMascaraDeEntrada("##/##/####"));
		txtPeriodoFinal.setColumns(6);
		txtPeriodoFinal.setHorizontalAlignment(JTextField.CENTER);
		txtPeriodoFinal.setEditable(false);
		JLabel labelIconePeriodoFinal = new JLabel(new ImageIcon("icon-calendar.png"));

		JLabel labelQuantidadeVagas = new JLabel("Quantidade de vagas");
		JSpinner spinnerQtdeVagasAmplaConcorrencia = new JSpinner();
		spinnerQtdeVagasAmplaConcorrencia.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		JFormattedTextField txtQtdVagasAmplaConcorrencia = ((JSpinner.NumberEditor) spinnerQtdeVagasAmplaConcorrencia
				.getEditor()).getTextField();
		((NumberFormatter) txtQtdVagasAmplaConcorrencia.getFormatter()).setAllowsInvalid(false);

		JSpinner spinnerQtdeVagasAcoesAfirmativas = new JSpinner();
		spinnerQtdeVagasAcoesAfirmativas.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		JFormattedTextField txtQtdVagasAcoesAfirmativas = ((JSpinner.NumberEditor) spinnerQtdeVagasAcoesAfirmativas
				.getEditor()).getTextField();
		((NumberFormatter) txtQtdVagasAcoesAfirmativas.getFormatter()).setAllowsInvalid(false);

		JSpinner spinnerQtdeVagasDeficiente = new JSpinner();
		spinnerQtdeVagasDeficiente.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		JFormattedTextField txtQtdVagasDeficiente = ((JSpinner.NumberEditor) spinnerQtdeVagasDeficiente.getEditor())
				.getTextField();
		((NumberFormatter) txtQtdVagasDeficiente.getFormatter()).setAllowsInvalid(false);

		JLabel labelCriterio = new JLabel("Critério");
		String[] criterio = { "1", "2", "3" };
		JComboBox<String> comboBoxCriterio = new JComboBox<String>(criterio);
		comboBoxCriterio.setEditable(false);

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
		qtdVagasAmplaConcorrencia.add(spinnerQtdeVagasAmplaConcorrencia);

		JPanel qtdVagasAcoesAfirmativas = new JPanel();
		qtdVagasAcoesAfirmativas.add(new JLabel("Ações afirmativas"));
		qtdVagasAcoesAfirmativas.add(spinnerQtdeVagasAcoesAfirmativas);

		JPanel qtdVagasDeficiente = new JPanel();
		qtdVagasDeficiente.add(new JLabel("Deficiente"));
		qtdVagasDeficiente.add(spinnerQtdeVagasDeficiente);

		JPanel quantitativos = new JPanel();
		quantitativos.add(qtdVagasAmplaConcorrencia);
		quantitativos.add(qtdVagasAcoesAfirmativas);
		quantitativos.add(qtdVagasDeficiente);

		Object[] options = { labelTitulo, txtTitulo, labelCampus, comboBoxCampus, labelCurso, comboBoxCursos,
				labelPublicoAlvo, txtPublicoAlvo, labelPeriodoEdital, periodoEdital, labelQuantidadeVagas,
				quantitativos, labelCriterio, comboBoxCriterio };
		JOptionPane.showMessageDialog(null, options, "CADASTRAR EDITAL", JOptionPane.PLAIN_MESSAGE);
		
		campusController.salvarEdital(txtTitulo, comboBoxCampus, comboBoxCursos, txtPublicoAlvo, txtPeriodoInicial, txtPeriodoFinal,
				txtQtdVagasAmplaConcorrencia, txtQtdVagasAcoesAfirmativas, txtQtdVagasDeficiente, comboBoxCriterio);

	}
	
	public void visualizarEdital () throws IOException {
		ListaLigadaSimples<Edital> listaDeEdital = campusController.retornarListaDeEditais();
		String col[] = { "ID", "TÍTULO", "CAMPUS", "CURSO", "PÚB. ALVO", "P. INICIAL", "P. FINAL", "QTD. VAGAS A.C.",
				"QTD. VAGAS A.A.", "QTD. VAGAS D.", "CRITÉRIO" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < listaDeEdital.getTamanho(); i++) {
			Object[] edital = { listaDeEdital.espiar(i).getId(), listaDeEdital.espiar(i).getTitulo(),
					listaDeEdital.espiar(i).getCampus(), listaDeEdital.espiar(i).getCurso(),
					listaDeEdital.espiar(i).getPublicoAlvo(), listaDeEdital.espiar(i).getPeriodoInicial(),
					listaDeEdital.espiar(i).getPeriodoFinal(), listaDeEdital.espiar(i).getAmplaConcorrencia(),
					listaDeEdital.espiar(i).getAcoesAfirmativas(), listaDeEdital.espiar(i).getDeficiente(),
					listaDeEdital.espiar(i).getCriterio() };
			tableModel.addRow(edital);
		}
		JTable table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "LISTA DE EDITAL", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void visualizarCandidatos () {
		campusController.listarCandidatos();
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
