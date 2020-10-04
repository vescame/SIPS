package edu.fatec.sips.dao;

import java.io.IOException;

import edu.fatec.sips.controller.ArquivoCampusController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Campus;

public class DAOCPS {
	ArquivoCampusController arquivoCampus = new ArquivoCampusController();
	
	public void salvar(final Campus campus){
		try {
			this.arquivoCampus.gravarCampus(campus);
		} catch (IOException e) {
			System.out.println("falha ao salvar candidato");
		}
	}
	
	public ListaLigadaSimples<Campus> listar() {
		try {
			return this.arquivoCampus.listarCampus();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
