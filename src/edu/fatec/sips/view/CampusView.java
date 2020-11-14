package edu.fatec.sips.view;

import java.awt.HeadlessException;
import java.io.IOException;
import java.text.SimpleDateFormat;

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

import edu.fatec.cronograma.view.ListarCronograma;
import edu.fatec.sips.controller.ArquivoCronogramaController;
import edu.fatec.sips.controller.CampusController;
import edu.fatec.sips.controller.EditalController;
import edu.fatec.sips.controller.ResultadoPreliminarController;
import edu.fatec.sips.data_structure.FilaImplementacaoDinamica;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Campus;
import edu.fatec.sips.model.CronogramaDeAtividades;
import edu.fatec.sips.model.Curso;
import edu.fatec.sips.model.Edital;

public class CampusView {
	ResultadoPreliminarController resultadoPreliminar = new ResultadoPreliminarController();
	CampusController campusController = new CampusController();
	ListarCronograma cronograma = new ListarCronograma();
	EditalController editalController = new EditalController();

	public int menuCampus(int opcao) throws NumberFormatException, HeadlessException, IOException {
		String textoMenu = "<html>" + "<head>" + "<style>" + "table {width: 300px; background-color: white;}"
				+ "table, th, td {border: 1px solid black;border-collapse: collapse;}" + "td { text-align: center}"
				+ "</style>" + "</head>" + "<body>" + "<table>" + "<tr><th colspan='2'>MENU CAMPUS</th></tr>"
				+ "<tr<th>Código</th><th>Opção</th></tr>" + "<tr><td>1</td><td>Cadastrar edital</td></tr>"
				+ "<tr><td>2</td><td>Editar edital</td></tr>" + "<tr><td>3</td><td>Visualizar edital</td></tr>"
				+ "<tr><td>4</td><td>Visualizar Candidatos</td></tr>"
				+ "<tr><td>5</td><td>Visualizar recursos</td></tr>"
				+ "<tr><td>6</td><td>Definir resultado preliminar</td></tr>"
				+ "<tr><td>7</td><td>Visualizar resultado preliminar</td></tr>"
				+ "<tr><td>8</td><td>Editar resultados finais</td></tr>"
				+ "<tr><td>9</td><td>Visualizar resultado final</td></tr>"
				+ "<tr><td>10</td><td>Visualizar cronograma de atividades</td></tr>"
				+ "<tr><td>99</td><td>Sair</td></tr>" + "</table>" + "</body>" + "</html>";
		if (opcao != 99) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(null, textoMenu,
					"SIPS - Sistema de Inscrição de Processo Seletivo", JOptionPane.PLAIN_MESSAGE));
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
			new ListarRecursos().listar();
			break;
		case 6:
//			resultadoPreliminar.gravarResultadoPreliminar();
			break;
		case 7:
//			resultadoPreliminar.visualizarResultadoPreliminar();
			break;
		case 8:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 9:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 10:
			cronograma.visualizarCronograDeAtividades();
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
		JComboBox<Campus> comboBoxCampus = carregarComboBoxCampus();
		comboBoxCampus.setEditable(false);

		JLabel labelCurso = new JLabel("Curso");
		JComboBox<Curso> comboBoxCursos = carregarComboBoxCurso();
		comboBoxCursos.setEditable(false);

		JLabel labelPublicoAlvo = new JLabel("Público alvo");
		JTextField txtPublicoAlvo = new JTextField();

		JLabel labelPeriodoEdital = new JLabel("Período");
		JLabel labelPeriodoInicial = new JLabel("Inicial");
		JTextField txtPeriodoInicial = new JFormattedTextField(carregarDataInicial());
		txtPeriodoInicial.setColumns(6);
		txtPeriodoInicial.setHorizontalAlignment(JTextField.CENTER);
		txtPeriodoInicial.setEditable(false);
		JLabel labelIconePeriodoInicial = new JLabel(new ImageIcon("icon-calendar.png"));

		JLabel labelPeriodoFinal = new JLabel("Final");
		JTextField txtPeriodoFinal = new JFormattedTextField(carregarDataFinal());
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

		int resposta = JOptionPane.showConfirmDialog(null, options, "CADASTRAR EDITAL", JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (resposta == JOptionPane.OK_OPTION) {
			Curso curso = (Curso) comboBoxCursos.getModel().getSelectedItem();
			Campus campus = (Campus) comboBoxCampus.getModel().getSelectedItem();
			campusController.salvarEdital(txtTitulo, campus, curso, txtPublicoAlvo, txtPeriodoInicial, txtPeriodoFinal,
					txtQtdVagasAmplaConcorrencia, txtQtdVagasAcoesAfirmativas, txtQtdVagasDeficiente, comboBoxCriterio);
		}

	}

	public void visualizarEdital() throws IOException {
		ListaLigadaSimples<Edital> listaDeEdital = editalController.listarEditais();
		String col[] = { "ID", "TÍTULO", "CAMPUS", "CURSO", "PÚBLICO ALVO", "PERÍODO INICIAL", "PERÍODO FINAL",
				"QTD. VAGAS AMPLA CONCORRÊNCIA", "QTD. VAGAS AÇÕES AFIRMATIVAS", "QTD. VAGAS DEFICIENTE", "CRITÉRIO" };
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
		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
		defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int numCol = 0; numCol < table.getColumnCount(); numCol++) {
			for (int i = 0; i <= numCol; i++) {
				table.getColumnModel().getColumn(i).setPreferredWidth(250);
				table.getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);
			}
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "LISTA DE EDITAL", JOptionPane.PLAIN_MESSAGE);
	}

	public void visualizarCandidatos() {
		campusController.listarCandidatos();
	}

	public JComboBox<Campus> carregarComboBoxCampus() throws IOException {
		ListaLigadaSimples<Campus> listaDeCampus = campusController.retornarListaDeCampus();
		JComboBox<Campus> campus = new JComboBox<Campus>();
		for (int i = 0; i < listaDeCampus.getTamanho(); i++) {
			campus.addItem(listaDeCampus.espiar(i));
		}
		return campus;
	}

	public JComboBox<Curso> carregarComboBoxCurso() throws IOException {
		ListaLigadaSimples<Curso> listaDeCurso = campusController.retornarListaDeCurso();
		JComboBox<Curso> cursos = new JComboBox<Curso>();
		for (int i = 0; i < listaDeCurso.getTamanho(); i++) {
			cursos.addItem(listaDeCurso.espiar(i));
		}
		return cursos;
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
}
