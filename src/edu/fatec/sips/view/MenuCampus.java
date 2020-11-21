package edu.fatec.sips.view;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JOptionPane;

import edu.fatec.sips.controller.CampusController;
import edu.fatec.sips.controller.ResultadoFinalController;

public class MenuCampus {

	public int menuCampus(int opcao) throws NumberFormatException, HeadlessException, IOException {
		String textoMenu = "<html>" + "<head>" + "<style>" + "table {width: 300px; background-color: white;}"
				+ "table, th, td {border: 1px solid black;border-collapse: collapse;}" + "td { text-align: center}"
				+ "</style>" + "</head>" + "<body>" + "<table>" + "<tr><th colspan='2'>MENU CAMPUS</th></tr>"
				+ "<tr<th>Código</th><th>Opção</th></tr>" + "<tr><td>1</td><td>Cadastrar edital</td></tr>"
				+ "<tr><td>2</td><td>Editar edital</td></tr>" + "<tr><td>3</td><td>Visualizar edital</td></tr>"
				+ "<tr><td>4</td><td>Visualizar Candidatos</td></tr>"
				+ "<tr><td>5</td><td>Visualizar recursos</td></tr>"
				+ "<tr><td>6</td><td>Definir resultado preliminar</td></tr>"
				+ "<tr><td>7</td><td>Visualizar resultado preliminar</td></tr>"
				+ "<tr><td>8</td><td>Editar resultados preliminares</td></tr>"
				+ "<tr><td>9</td><td>Definir resultado final</td></tr>"
				+ "<tr><td>10</td><td>Visualizar resultado final</td></tr>"
				+ "<tr><td>11</td><td>Visualizar cronograma de atividades</td></tr>"
				+ "<tr><td>99</td><td>Sair</td></tr>" + "</table>" + "</body>" + "</html>";
		if (opcao != 99) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(null, textoMenu,
					"SIPS - Sistema de Inscrição de Processo Seletivo", JOptionPane.PLAIN_MESSAGE));
			avaliarOpcao(opcao);
			return menuCampus(opcao);
		} else {
			return opcao;
		}
	}

	public void avaliarOpcao(int opcao) throws NumberFormatException, HeadlessException, IOException {
		switch (opcao) {
		case 1:
			new CadastrarEdital().cadastrarEdital();
			break;
		case 2:
			new EditarEdital().editarEdital();
			break;
		case 3:
			new VisualizarEdital().visualizarEdital();
			break;
		case 4:
			new CampusController().listarCandidatos();
			break;
		case 5:
			new ListarRecursos().listar();
			break;
		case 6:
			new ResultadoPreliminarView().definirResultadoPreliminar();
			break;
		case 7:
			new ResultadoPreliminarView().visualizarResultadoPreliminar();
			break;
		case 8:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 9:
			new ResultadoFinalController().iniciarResultado();
			break;
		case 10:
			JOptionPane.showMessageDialog(null, "FUNÇÃO SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
			break;
		case 11:
			new ListarCronograma().visualizarCronograDeAtividades();
			break;
		case 99:
			JOptionPane.showMessageDialog(null, "Encerrando sessão...");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Opção Inválida !!!");
		}
	}
}
