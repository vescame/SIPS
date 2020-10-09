package edu.fatec.sips.dao;

import java.io.IOException;

import edu.fatec.sips.controller.ArquivoEditalController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Edital;

public class DAOEDITAL {
ArquivoEditalController arquivoEdital = new ArquivoEditalController();
	
	public void salvar(final Edital edital){
		try {
			this.arquivoEdital.gravarEdital(edital);
		} catch (IOException e) {
			System.out.println("falha ao salvar candidato");
		}
	}
	
	public ListaLigadaSimples<Edital> listar() {
		try {
			return this.arquivoEdital.listarEditais();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
