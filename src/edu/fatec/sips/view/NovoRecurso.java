package edu.fatec.sips.view;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import edu.fatec.sips.controller.CandidatoController;
import edu.fatec.sips.controller.DocumentoCandidatoController;
import edu.fatec.sips.controller.RecursoController;
import edu.fatec.sips.enums.Etapa;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Documento;
import edu.fatec.sips.model.Recurso;

public class NovoRecurso {
	private final DocumentoCandidatoController ctrlDocumentosCandidato;
	private final CandidatoController ctrlCandidatos;
	private final RecursoController ctrlRecursos;

	public NovoRecurso() {
		this.ctrlDocumentosCandidato = new DocumentoCandidatoController();
		this.ctrlRecursos = new RecursoController();
		this.ctrlCandidatos = new CandidatoController();

		try {
			final Candidato candidato = this.escolherCandidato(null);

			final Etapa etapa = this.escolherEtapa();

			final String descricao = this.descricaoRecurso();

			final Recurso recurso = new Recurso();

			recurso.setIdCandidato(candidato.getId());
			recurso.setDescricao(descricao);
			recurso.setEtapaRecurso(etapa);

			this.ctrlRecursos.abrirRecurso(recurso);
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

	private Etapa escolherEtapa() {
		JLabel lblEtapa = new JLabel("Etapa");
		String[] etapas = { Etapa.INSCRICAO.toString(), Etapa.ANALISE_CURRICULO.toString(), Etapa.ENTREVISTA.toString(),
				Etapa.RESULTADO_PRELIMINAR.toString() };

		JComboBox<String> comboBoxEtapas = new JComboBox<String>(etapas);
		comboBoxEtapas.setEditable(true);

		Object[] campos = { lblEtapa, comboBoxEtapas };

		JOptionPane.showMessageDialog(null, campos, "Etapa", JOptionPane.PLAIN_MESSAGE);

		Etapa etapaSelecionada = null;
		for (Etapa e : Etapa.values()) {
			if (e.toString().equals(comboBoxEtapas.getSelectedItem().toString())) {
				etapaSelecionada = e;
			}
		}

		return etapaSelecionada;
	}

	private String descricaoRecurso() {
		return JOptionPane.showInputDialog("Solicitação de recurso");
	}
}
