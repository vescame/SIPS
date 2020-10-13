package edu.fatec.sips.controller;

import java.io.IOException;

import javax.swing.JTextField;

import edu.fatec.sips.dao.DAOCPS;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Campus;

public class CPSController {
	ArquivoCampusController arquivoCampusController = new ArquivoCampusController();
	Campus campus = new Campus();
	DAOCPS daocps = new DAOCPS();

	public void salvarCampus(JTextField txtNomeFaculdade, JTextField txtUnidadeFaculdade) throws IOException {
		campus.setNome(txtNomeFaculdade.getText());
		campus.setUnidade(txtUnidadeFaculdade.getText());
		daocps.salvar(campus);
	}

	public ListaLigadaSimples<Campus> listarCampus() throws IOException {
		ListaLigadaSimples<Campus> listaDeCampus = this.arquivoCampusController.listarCampus();
		return listaDeCampus;
	}
}
