package edu.fatec.sips.view;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JOptionPane;

import edu.fatec.sips.view.candidato.MenuCandidato;

public class MenuPrincipal {

	MenuCandidato menuCandidato = new MenuCandidato();
	CPSView cpsView = new CPSView();
	CampusView campusView = new CampusView();

	public int menuPrincipal(int opcao) throws NumberFormatException, HeadlessException, IOException {
		if (opcao != 99) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("MENU PRINCIPAL \n\n" + "1 - Menu Candidato\n"
					+ "2 - Menu CPS\n" + "3 - Menu Campus\n" + "99 - Finalizar"));
			avaliarOpcao(opcao);
			return menuPrincipal(opcao);
		} else {
			return opcao;
		}
	}

	public void avaliarOpcao(int opcao) throws NumberFormatException, HeadlessException, IOException {
		switch (opcao) {
		case 1:
			menuCandidato.menu();
			break;
		case 2:
			cpsView.menuCPS(0);
			break;
		case 3:
			campusView.menuCampus(0);
			break;
		case 99:
			JOptionPane.showMessageDialog(null, "Finalizando SIPS ...");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Opção Inválida !!!");
		}
	}
}
