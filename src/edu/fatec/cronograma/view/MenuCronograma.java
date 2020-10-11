package edu.fatec.cronograma.view;

import java.io.IOException;

import javax.swing.JOptionPane;

public class MenuCronograma {

	CadastrarCronograma cadastrarCronograma = new CadastrarCronograma();
	ListarCronograma listarCronograma = new ListarCronograma();
	
	public void menu() throws IOException {
		int opc = 0;
		while (opc != 99) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("\nCADASTRAR CRONOGRAMA DE ATIVIDADES: "
					+ "\n1- Inserir Atividades no Cronograma" + "\n2- Visualizar Cronograma" + "\n99- Sair"));

			switch (opc) {
			case 1:
				cadastrarCronograma.Cadastrar();
				break;
			case 2:
				listarCronograma.listar();;
				break;
			case 99:
				JOptionPane.showMessageDialog(null, "Encerrando Sessão...");
				break;
			default: 
				JOptionPane.showMessageDialog(null, "Opção Inválida !!!");
			}
		}

	}

}
