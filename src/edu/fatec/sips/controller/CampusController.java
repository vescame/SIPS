package edu.fatec.sips.controller;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Campus;

public class CampusController {
	
	ListaLigadaSimples<Campus> listaDeCampus = new ListaLigadaSimples<Campus>();
	
	public void adicionarCampus (int numeroDeCampus) {
		for (int i = 1; i <= numeroDeCampus ; i++) {
			Campus campus = new Campus (i, "Fatec", "Leste " + i);
			listaDeCampus.adicionar(campus);
		}
	}
	
	public void visualizarCampus() {
		listaDeCampus.printar();
	}
	
}
