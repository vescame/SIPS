package edu.fatec.sips;

import java.io.IOException;

import javax.swing.JOptionPane;

import edu.fatec.sips.controller.ArquivoController;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.view.CampusView;

public class MenuPrincipal {
	public static void main(String[] args) throws IOException {
//		Candidato[] candidato = new Candidato[1];
//		ArquivoController ac = new ArquivoController();
//		int opc = 0;
//		while (opc != 9) {
//			opc = Integer.parseInt(JOptionPane.showInputDialog("1 - Ler Candidato\n2 - Gravar Candidato\n9 - Finalizar"));
//			switch (opc) {
//			case 1:
//				ac.LerCandidato(candidato);
//				break;
//			case 2:
//				candidato = ac.GravarCandidato(candidato);
//				break;
//			case 9:
//				JOptionPane.showMessageDialog(null, "Finalizando SIPS...");
//				break;
//			default:
//				JOptionPane.showMessageDialog(null, "OPCAO INVALIDA");
//			}
//		}
		CampusView campusView = new CampusView();
		campusView.menuCampus(0);
	}
}
