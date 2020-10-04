package edu.fatec.cronograma.view;

import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import edu.fatec.sips.controller.CronogramaController;
import edu.fatec.sips.model.CronogramaDeAtividades;

public class CadastrarCronograma {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	CronogramaController cronoController = new CronogramaController();
	

	public void Cadastrar() {

		CronogramaDeAtividades atividades = new CronogramaDeAtividades();
		atividades.setIdAtividade(Integer.parseInt(JOptionPane.showInputDialog("Insira o id da atividade: ")));
		atividades.setDescricaoAtividade(JOptionPane.showInputDialog("Descreva qual será a atividade: "));
		Date inicio, entrega;
		try {
			inicio = sdf.parse(JOptionPane.showInputDialog("Insira a data de inicio: "));
			atividades.setDataInicio(inicio);
			entrega = sdf.parse(JOptionPane.showInputDialog("Insira a data de entrega: "));
			atividades.setDataEntrega(entrega);
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cronoController.inserirAtividade(atividades);
		int opcao = JOptionPane.showConfirmDialog( null,"Deseja gravar o arquivo", "Gravação",JOptionPane.YES_NO_OPTION);
		if(opcao==JOptionPane.YES_OPTION) {			
			System.out.println(cronoController.mostrarAtividades());
		} else {
		}
	}

}
