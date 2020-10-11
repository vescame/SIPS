package edu.fatec.sips.view.candidato;

import javax.swing.JOptionPane;

public class MenuCandidato {
	public void menu() {
		int opc = 0;
		while (opc != 99) {
			opc = Integer.parseInt(JOptionPane
					.showInputDialog("MENU CANDIDATO \n\n 1 - Ler Candidato\n2 - Gravar Candidato\n99 - Finalizar"));
			switch (opc) {
			case 1:
				new ListarCandidatos().listar();
				break;
			case 2:
				new CadastrarCandidato().cadastrar();
				break;
			case 99:
				JOptionPane.showMessageDialog(null, "Encerrando sessão...");
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção Inválida !!!");
			}
		}
	}
}
