package edu.fatec.sips.view;

import javax.swing.JOptionPane;

import edu.fatec.sips.controller.CandidatoController;
import edu.fatec.sips.controller.DocumentoCandidatoController;
import edu.fatec.sips.controller.ResultadoFinalController;
import edu.fatec.sips.enums.Etapa;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Documento;
import edu.fatec.sips.model.ResultadoFinal;

public class ResultadosCandidato {
	private final DocumentoCandidatoController ctrlDocumentosCandidato;
	private final CandidatoController ctrlCandidatos;

	public ResultadosCandidato(final Etapa etapa) {
		this.ctrlDocumentosCandidato = new DocumentoCandidatoController();
		this.ctrlCandidatos = new CandidatoController();

		try {
			final Candidato candidato = this.escolherCandidato(null);
			if (etapa == Etapa.RESULTADO_FINAL) {
				ResultadoFinal resFinal = new ResultadoFinalController().getPorCandidato(candidato.getId());
				mostrarResultado(resFinal.isAprovado());
			} else if (etapa == Etapa.RESULTADO_PRELIMINAR) {
				// ResultadoPreliminarView resPreliminar = new edu.fatec.sips.controller.ResultadoPreliminar()
					// .getPorCandidato(candidato.getDocumentos().espiar(0).toString());
			}
			
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

	private void mostrarResultado(final boolean aprovado) {
		String mensagem = aprovado ? "Você está aprovado!" : "Você não foi aprovado dessa vez.";
		JOptionPane.showMessageDialog(null, mensagem);
	}
}
