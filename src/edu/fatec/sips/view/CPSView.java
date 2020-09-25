package edu.fatec.sips.view;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JOptionPane;

import edu.fatec.sips.controller.CPSController;

public class CPSView {

	CPSController cpsController = new CPSController();

	public int menuCPS(int opcao) throws NumberFormatException, HeadlessException, IOException {
		if (opcao != 9) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(
					"Menu CPS\n 1- Definir vagas remanescentes \n" + " 2- Cadastrar campus\n 3- Visualizar campus \n"
							+ " 4- Cadastrar cronograma de atividades \n" + " 9- Sair"));
			avaliarOpcao(opcao);
			return menuCPS(opcao);
		} else {
			return opcao;
		}
	}

	public void avaliarOpcao(int opcao) throws NumberFormatException, HeadlessException, IOException {
		switch (opcao) {
		case 1:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 2:
			cpsController.cadastrarCampus(
					Integer.parseInt(JOptionPane.showInputDialog("Quantidade de campus, a serem adicionados")));
			break;
		case 3:
			cpsController.visualizarCampus();
			break;
		case 4:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 9:
			JOptionPane.showMessageDialog(null, "Encerrando sessão...");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Opção Inválida !!!");
		}
	}

}
