package edu.fatec.sips.view.candidato;

import javax.swing.JOptionPane;

import edu.fatec.sips.controller.CandidatoController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;

public class ListarCandidatos {
	CandidatoController candidatoController = new CandidatoController();

	public void listar() {
		final ListaLigadaSimples<Candidato> candidatos = candidatoController.listarCandidatos();
		for (int i = 0; i < candidatos.getSize(); ++i) {
			JOptionPane.showMessageDialog(null, candidatos.espiar(i));
		}
	}
}
