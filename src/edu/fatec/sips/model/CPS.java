package edu.fatec.sips.model;

import edu.fatec.sips.data_structure.ListaLigadaSimples;

public class CPS {

	ListaLigadaSimples<Campus> listaCampus;
	ListaLigadaSimples<CronogramaDeAtividades> cronogramaDeAtividades;

	public CPS(ListaLigadaSimples<Campus> listaCampus,
			ListaLigadaSimples<CronogramaDeAtividades> cronogramaDeAtividades) {
		this.listaCampus = listaCampus;
		this.cronogramaDeAtividades = cronogramaDeAtividades;
	}

}
