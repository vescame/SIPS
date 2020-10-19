package edu.fatec.sips.view;

import java.awt.HeadlessException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JOptionPane;

public class MenuPrincipal {

	MenuCandidato menuCandidato = new MenuCandidato();
	CPSView cpsView = new CPSView();
	CampusView campusView = new CampusView();

	public int menuPrincipal(int opcao) throws NumberFormatException, HeadlessException, IOException, ParseException {
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
				+ "<tr><th colspan='2'>MENU PRINCIPAL</th></tr>"
				+ "<tr<th>Código</th><th>Opção</th></tr>"
				+ "<tr><td>1</td><td>Menu Candidato</td></tr>"
				+ "<tr><td>2</td><td>Menu CPS (Comissão do Processo Seletivo)</td></tr>"
				+ "<tr><td>3</td><td>Menu Campus</td></tr>"
				+ "<tr><td>99</td><td>Sair</td></tr>"
				+ "</table>"
				+ "</body>"
				+ "</html>";
		if (opcao != 99) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(null, textoMenu,"SIPS - Sistema de Inscrição de Processo Seletivo", JOptionPane.PLAIN_MESSAGE));
			avaliarOpcao(opcao);
			return menuPrincipal(opcao);
		} else {
			return opcao;
		}
	}

	public void avaliarOpcao(int opcao) throws NumberFormatException, HeadlessException, IOException, ParseException {
		switch (opcao) {
		case 1:
			menuCandidato.menuCandidato(0);
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
