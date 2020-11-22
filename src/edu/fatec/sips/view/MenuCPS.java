package edu.fatec.sips.view;

import java.awt.HeadlessException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JOptionPane;

public class MenuCPS {
    public int menuCPS(int opcao) throws NumberFormatException, HeadlessException, IOException, ParseException {
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
                + "<tr><th colspan='2'>MENU CPS (Comiss�o do Processo Seletivo)</th></tr>"
                + "<tr<th>C�digo</th><th>Op��o</th></tr>"
                + "<tr><td>1</td><td>Cadastrar Campus</td></tr>"
				+ "<tr><td>2</td><td>Visualizar Campus</td></tr>"
                + "<tr><td>3</td><td>Gerenciar Cronograma de Atividades</td></tr>"
				+ "<tr><td>99</td><td>Sair</td></tr>"
                + "</table>"
				+ "</body>"
				+ "</html>";
        if (opcao != 99) {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(null, textoMenu,
                    "SIPS - Sistema de Inscri��o de Processo Seletivo", JOptionPane.PLAIN_MESSAGE));
            avaliarOpcao(opcao);
            return menuCPS(opcao);
        } else {
            return opcao;
        }
    }

    public void avaliarOpcao(int opcao) throws NumberFormatException, HeadlessException, IOException, ParseException {
        switch (opcao) {
            case 1:
                JOptionPane.showMessageDialog(null, "FUN��O SENDO DESENVOLVIDA \n\n Tente mais tarde :)");
                break;
            case 2:
                new CadastrarCampus().cadastrarCampus();
                break;
            case 3:
                new VisualizarCampus().visualizarCampus();
                break;
            case 4:
                new MenuCronograma().menuCronogramaDeAtividades(0);
                break;
            case 99:
                JOptionPane.showMessageDialog(null, "Encerrando sess�o...");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Op��o Inv�lida !!!");
        }
    }
}
