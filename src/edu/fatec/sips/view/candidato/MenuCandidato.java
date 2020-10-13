package edu.fatec.sips.view.candidato;

import javax.swing.JOptionPane;

import edu.fatec.cronograma.view.ListarCronograma;

public class MenuCandidato {
	public void menu() {
		ListarCronograma listarCronograma = new ListarCronograma();
		int opc = 0;
		while (opc != 99) {
			opc = Integer.parseInt(JOptionPane.showInputDialog(
					"MENU CANDIDATO \n\n" +
					"1 - Visualizar Cronograma de Atividades\n" +
					"2 - Visualizar Datas de Entrevistas\n" +
					"3 - Visualizar Resultado Parcial\n" +
					"4 - Visualizar Resultado Final\n" +
					"5 - Solicitar Recurso\n" +
					"99 - Finalizar"));
			switch (opc) {
			case 1:
				listarCronograma.listar();
				break;
			case 2:
				// new VisualizarResultadoParcial().resultadoParcial();
				break;
			case 99:
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção Inválida !!!");
			}
		}
	}
}
