package edu.fatec.sips.view.candidato;

import javax.swing.JOptionPane;

public class MenuCandidato {
	public MenuCandidato() {
		int opc = 0;
		while (opc != 9) {
			opc = Integer
					.parseInt(JOptionPane.showInputDialog("1 - Ler Candidato\n2 - Gravar Candidato\n9 - Finalizar"));
			switch (opc) {
			case 1:
				new ListarCandidatos().listar();
				break;
			case 2:
				new CadastrarCandidato().cadastrar();
				break;
			case 9:
				JOptionPane.showMessageDialog(null, "Encerrando menu de candidatos");
				break;
			default:
				JOptionPane.showMessageDialog(null, "OPCAO INVALIDA");
			}
		}
	}
}
