package edu.fatec.sips;

import java.io.IOException;

import javax.swing.JOptionPane;

import edu.fatec.sips.view.candidato.MenuCandidato;

public class MenuPrincipal {
	
	public static void main(String[] args) throws IOException {
		int opc = 0;
		while (opc != 9) {
			opc = Integer
					.parseInt(JOptionPane.showInputDialog("1 - Menu Candidato\n" +
							"2 - Menu Câmpus\n" +
							"9 - Finalizar"));
			switch (opc) {
			case 1:
				new MenuCandidato();
				break;
			case 2:
				// new MenuCampus();
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "Finalizando SIPS...");
				break;
			default:
				JOptionPane.showMessageDialog(null, "OPCAO INVALIDA");
			}
		}
//		CampusView campusView = new CampusView();
//		campusView.menuCampus(0);
	}
}
