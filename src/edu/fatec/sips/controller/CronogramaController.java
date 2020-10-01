package edu.fatec.sips.controller;

import edu.fatec.sips.data_structure.FilaImplementacaoDinamica;
import edu.fatec.sips.model.CronogramaDeAtividades;

public class CronogramaController {

	private FilaImplementacaoDinamica<CronogramaDeAtividades> filadinamica = new FilaImplementacaoDinamica<CronogramaDeAtividades>();
	
	public void inserirAtividade(CronogramaDeAtividades atividades) {
		filadinamica.inserirNoFinal(atividades);
	}
	public void removerAtividade() {
		filadinamica.removerNoInicio();
	}
	
	public String mostrarAtividades() {
		return filadinamica.printFila();
	}	
	
}
