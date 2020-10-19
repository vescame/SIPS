package edu.fatec.cronograma.view;

import java.io.IOException;
import java.text.ParseException;

import javax.swing.JOptionPane;

public class MenuCronograma {

	CadastrarCronograma cadastrarCronograma = new CadastrarCronograma();
	ListarCronograma listarCronograma = new ListarCronograma();
	
	public int menuCronogramaDeAtividades(int opcao) throws IOException, ParseException {
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
				+ "<tr><th colspan='2'>CRONOGRAMA DE ATIVIDADES</th></tr>"
				+ "<tr<th>Código</th><th>Opção</th></tr>"
				+ "<tr><td>1</td><td>Cadastrar Cronograma de Atividades</td></tr>"
				+ "<tr><td>2</td><td>Visualizar Cronograma de Atividades</td></tr>"
				+ "<tr><td>99</td><td>Sair</td></tr>"
				+ "</table>"
				+ "</body>"
				+ "</html>";
		if (opcao != 99) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog(null, textoMenu,"SIPS - Sistema de Inscrição de Processo Seletivo", JOptionPane.PLAIN_MESSAGE));
			avaliarOpcao(opcao);
			return menuCronogramaDeAtividades(opcao);
		}else {
			return opcao;
		}
	}
	
	public void avaliarOpcao (int opcao) throws ParseException, IOException {
		switch (opcao) {
		case 1:
			cadastrarCronograma.cadastrarCronogramaDeAtividades();
			break;
		case 2:
			listarCronograma.visualizarCronograDeAtividades();
			break;
		case 99:
			JOptionPane.showMessageDialog(null, "Encerrando Sessão...");
			break;
		default: 
			JOptionPane.showMessageDialog(null, "Opção Inválida !!!");
		}
	}

}
