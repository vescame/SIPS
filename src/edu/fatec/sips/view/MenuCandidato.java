package edu.fatec.sips.view;

import javax.swing.JOptionPane;

import edu.fatec.cronograma.view.ListarCronograma;

public class MenuCandidato {
	
	public int menuCandidato(int opcao) {
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
				+ "<tr><th colspan='2'>MENU CANDIDATO</th></tr>"
				+ "<tr<th>Código</th><th>Opção</th></tr>"
				+ "<tr><td>1</td><td>Visualizar Cronograma de Atividades</td></tr>"
				+ "<tr><td>2</td><td>Visualizar Resultado Preliminar</td></tr>"
				+ "<tr><td>3</td><td>Visualizar Resultado Final</td></tr>"
				+ "<tr><td>4</td><td>Solicitar Recurso</td></tr>"
				+ "<tr><td>99</td><td>Sair</td></tr>"
				+ "</table>"
				+ "</body>"
				+ "</html>";
		if (opcao != 99) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(null, textoMenu,"SIPS - Sistema de Inscrição de Processo Seletivo", JOptionPane.PLAIN_MESSAGE));
			avaliarOpcao(opcao);
			return menuCandidato(opcao);
		}else {
			return opcao;
		}
	}
	
	public void avaliarOpcao (int opcao) {
		ListarCronograma listarCronograma = new ListarCronograma();
		switch (opcao) {
		case 1:
			listarCronograma.visualizarCronograDeAtividades();
			break;
		case 2:
			// new VisualizarResultadoParcial().resultadoParcial();
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 4:
			new NovoRecurso();
			break;
		case 99:
			JOptionPane.showMessageDialog(null, "Encerrando sessão...");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Opção Inválida !!!");
		}
	}
}
