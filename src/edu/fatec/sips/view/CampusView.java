package edu.fatec.sips.view;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JOptionPane;
import edu.fatec.sips.controller.CampusController;

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
			campusController.cadastrarEdital();
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 3:
			campusController.visualizarEdital();
			break;
		case 4:
			campusController.visualizarCandidatos();
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
}
