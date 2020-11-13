package edu.fatec.sips.view;

import javax.swing.JOptionPane;

import edu.fatec.sips.controller.CandidatoController;
import edu.fatec.sips.controller.DocumentoCandidatoController;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Documento;

public class DesistenciaCandidato {
	private final DocumentoCandidatoController ctrlDocumentosCandidato;
	private final CandidatoController ctrlCandidatos;

	public DesistenciaCandidato() {
		this.ctrlDocumentosCandidato = new DocumentoCandidatoController();
		this.ctrlCandidatos = new CandidatoController();

		try {
			final Candidato candidato = this.escolherCandidato(null);
			candidato.setDesistente(entradaDesistencia());
			this.ctrlCandidatos.atualizar(candidato);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Operação Cancelada");
		}
	}

	private Candidato escolherCandidato(String documento) {
		String numeroDocumento = JOptionPane.showInputDialog("Insira seu documento:").trim();

		try {
			Documento documentoCandidato = ctrlDocumentosCandidato.getPorNumeroDocumento(numeroDocumento);

			if (documentoCandidato != null) {
				return this.ctrlCandidatos.getPorId(documentoCandidato.getIdCandidato());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, "Documento inválido");
		return escolherCandidato(null);
	}

	private boolean entradaDesistencia() {
		final int resposta = JOptionPane.showConfirmDialog(null, "Concordo em desistir", "Desistir?",
				JOptionPane.YES_OPTION);
		return resposta == JOptionPane.YES_OPTION;
	}
}
