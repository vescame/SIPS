package edu.fatec.cronograma.view;

import edu.fatec.sips.controller.CronogramaController;

public class ListarCronograma {


	CronogramaController cronoController = new CronogramaController();

	public void visualizar() {
		System.out.println(cronoController.mostrarAtividades());
	}
	
}
