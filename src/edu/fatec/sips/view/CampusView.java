package edu.fatec.sips.view;

import javax.swing.JOptionPane;

import edu.fatec.sips.controller.CampusController;

public class CampusView {
	
	CampusController campusController = new CampusController();
	
	public int menuCampus (int opcao) {
		if (opcao != 99) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("Gerenciar Campus\n 1- Adicionar Campus\n 2- Visualizar Campus\n 99- Sair"));
			avaliarOpcao(opcao);
			return menuCampus(opcao); 
		} else {
			return opcao;
		}
	}
	
	public void avaliarOpcao (int opcao) {
		switch (opcao) {
		case 0:
			JOptionPane.showMessageDialog(null, "Bem vindo, ao gerenciamento de Campus.");
			break;
		case 1:
			campusController.adicionarCampus(Integer.parseInt(JOptionPane.showInputDialog("Quantidade de campus, a serem adicionados")));
			break;
		case 2:
			campusController.visualizarCampus();
			break;
		case 99:
			JOptionPane.showMessageDialog(null, "Saindo ;)");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Opção Inválida !!!");
		}
	}
	
}
