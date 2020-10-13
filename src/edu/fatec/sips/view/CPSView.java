package edu.fatec.sips.view;

import java.awt.HeadlessException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.fatec.cronograma.view.MenuCronograma;
import edu.fatec.sips.controller.CPSController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Campus;

public class CPSView {

	CPSController cpsController = new CPSController();
	MenuCronograma cronograma = new MenuCronograma();

	public int menuCPS(int opcao) throws NumberFormatException, HeadlessException, IOException, ParseException {
		if (opcao != 99) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(
					"MENU CPS \n\n 1- Definir vagas remanescentes \n" + " 2- Cadastrar campus\n 3- Visualizar campus \n"
							+ " 4- Gerenciar cronograma de atividades \n" + " 99- Sair"));
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
			cronograma.menu();
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
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "LISTA DE CAMPUS", JOptionPane.PLAIN_MESSAGE);
	}

}
