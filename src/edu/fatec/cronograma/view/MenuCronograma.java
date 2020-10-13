package edu.fatec.cronograma.view;

import java.io.IOException;
import java.text.ParseException;

import javax.swing.JOptionPane;

public class MenuCronograma {

	CadastrarCronograma cadastrarCronograma = new CadastrarCronograma();
	ListarCronograma listarCronograma = new ListarCronograma();
	
	public void menu() throws IOException, ParseException {
		int opc = 0;
		while (opc != 99) {
			opc = Integer.parseInt(JOptionPane.showInputDialog("\nCRONOGRAMA DE ATIVIDADES: "
					+ "\n1- Cadastrar cronograma de atividades" + "\n2- Visualizar cronograma de atividades" + "\n99- Sair"));

			switch (opc) {
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

}
