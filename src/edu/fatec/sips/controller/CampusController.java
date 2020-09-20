package edu.fatec.sips.controller;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Campus;

public class CampusController {
	
	ListaLigadaSimples<Campus> listaDeCampus = new ListaLigadaSimples<Campus>();
	
	public void adicionarCampus (int numeroDeCampos) {
		for (int i = 1; i <= numeroDeCampos ; i++) {
			Campus campus = new Campus (i, "Fatec", "Leste " + i);
			listaDeCampus.add(campus);
		}
	}
	
	public void visualizarCampus() {
		listaDeCampus.print();
	}
	
}
