package edu.fatec.sips.controller;

import java.io.IOException;

import javax.swing.JTextField;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.file_controller.ArquivoCampusController;
import edu.fatec.sips.file_controller.ArquivoCpsController;
import edu.fatec.sips.model.Campus;

public class CPSController {
	ArquivoCampusController arquivoCampusController = new ArquivoCampusController();
	Campus campus = new Campus();
	ArquivoCpsController arquivoCpsController = new ArquivoCpsController();

	public void salvarCampus(JTextField txtNomeFaculdade, JTextField txtUnidadeFaculdade) throws IOException {
		campus.setNome(txtNomeFaculdade.getText());
		campus.setUnidade(txtUnidadeFaculdade.getText());
		arquivoCpsController.salvar(campus);
	}

	public ListaLigadaSimples<Campus> listarCampus() throws IOException {
		ListaLigadaSimples<Campus> listaDeCampus = this.arquivoCampusController.listarCampus();
		return listaDeCampus;
	}
}
