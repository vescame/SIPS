package edu.fatec.sips.view;

import java.awt.HeadlessException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.fatec.cronograma.view.MenuCronograma;
import edu.fatec.sips.controller.CPSController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Campus;

public class CPSView {

	CPSController cpsController = new CPSController();
	MenuCronograma cronograma = new MenuCronograma();

	public int menuCPS(int opcao) throws NumberFormatException, HeadlessException, IOException, ParseException {
		String textoMenu = "<html>"
				+ "<head>"
				+ "<style>"
				+ "table {width: 300px; background-color: white;}"
				+ "table, th, td {border: 1px solid black;border-collapse: collapse;}"
				+ "td { text-align: center}"
				+ "</style>"
				+ "</head>"
				+ "<body>"
				+ "<table>"
				+ "<tr><th colspan='2'>MENU CPS (Comissão do Processo Seletivo)</th></tr>"
				+ "<tr<th>Código</th><th>Opção</th></tr>"
				+ "<tr><td>1</td><td>Definir Vagas Remanescentes</td></tr>"
				+ "<tr><td>2</td><td>Cadastrar Campus</td></tr>"
				+ "<tr><td>3</td><td>Visualizar Campus</td></tr>"
				+ "<tr><td>4</td><td>Gerenciar Cronograma de Atividades</td></tr>"
				+ "<tr><td>99</td><td>Sair</td></tr>"
				+ "</table>"
				+ "</body>"
				+ "</html>";
		if (opcao != 99) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(null, textoMenu,"SIPS - Sistema de Inscrição de Processo Seletivo", JOptionPane.PLAIN_MESSAGE));
			avaliarOpcao(opcao);
			return menuCPS(opcao);
		} else {
			return opcao;
		}
	}

	public void avaliarOpcao(int opcao) throws NumberFormatException, HeadlessException, IOException, ParseException {
		switch (opcao) {
		case 1:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 2:
			cadastrarCampus();
			break;
		case 3:
			visualizarCampus();
			break;
		case 4:
			cronograma.menuCronogramaDeAtividades(0);
			break;
		case 99:
			JOptionPane.showMessageDialog(null, "Encerrando sessão...");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Opção Inválida !!!");
		}
	}

	public void cadastrarCampus() throws IOException {
		JLabel labelNomeFaculdade = new JLabel("Nome");
		JLabel labelUnidadeFaculdade = new JLabel("Unidade");

		JTextField txtNomeFaculdade = new JTextField();
		JTextField txtUnidadeFaculdade = new JTextField();

		Object[] options = { labelNomeFaculdade, txtNomeFaculdade, labelUnidadeFaculdade, txtUnidadeFaculdade };

		JOptionPane.showMessageDialog(null, options, "CADASTRAR CAMPUS", JOptionPane.PLAIN_MESSAGE);

		cpsController.salvarCampus(txtNomeFaculdade, txtUnidadeFaculdade);
	}

	public void visualizarCampus() throws IOException {
		ListaLigadaSimples<Campus> listaDeCampus = cpsController.listarCampus();
		String col[] = { "ID", "NOME", "UNIDADE" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < listaDeCampus.getTamanho(); i++) {
			Object[] campus = { listaDeCampus.espiar(i).getId(), listaDeCampus.espiar(i).getNome(),
					listaDeCampus.espiar(i).getUnidade() };
			tableModel.addRow(campus);
		}
		JTable table = new JTable(tableModel);
		DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
		rendar.setHorizontalAlignment(JLabel.CENTER);
		for (int numCol = 0; numCol < table.getColumnCount(); numCol++) {
			for (int i = 0; i <= numCol; i++) {
				table.getColumnModel().getColumn(i).setPreferredWidth(250);
				table.getColumnModel().getColumn(i).setCellRenderer(rendar);
			}
		}
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "LISTA DE CAMPUS", JOptionPane.PLAIN_MESSAGE);
	}

}
