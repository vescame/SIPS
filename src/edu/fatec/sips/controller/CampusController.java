package edu.fatec.sips.controller;

import edu.fatec.sips.view.candidato.ListarCandidatos;

public class CampusController {
	
	public void visualizarCandidatos() {
		new ListarCandidatos().listar();
	}
}
