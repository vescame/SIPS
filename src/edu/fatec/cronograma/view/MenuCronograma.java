package edu.fatec.cronograma.view;

import javax.swing.JOptionPane;

public class MenuCronograma {

	CadastrarCronograma cadastrarCronograma = new CadastrarCronograma();
	ListarCronograma listarCronograma = new ListarCronograma();
	
	public void menu() {
		int opc = 0;
		while (opc != 9) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("\nESCOLHA UMA OPÇÃO: "
					+ "\n1) Inserir Atividades no Cronograma" + "\n2) Visualizar Cronograma" + "\n9) Sair"));

			switch (opc) {
			case 1:
				cadastrarCronograma.Cadastrar();
				break;
			case 2:
				listarCronograma.visualizar();
				break;
			case 9:
				break;
			default: 
				JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA");
			}
		}

	}

}
