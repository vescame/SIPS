package edu.fatec.sips.view.candidato;

import javax.swing.JOptionPane;

import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Documento;
import edu.fatec.sips.model.Nota;
import edu.fatec.sips.model.Recurso;

public class VisualizarCandidato {
	private Candidato candidato;

	public VisualizarCandidato(final Candidato candidato) {
		this.candidato = candidato;
	}

	public void mostrarTela() {
		JOptionPane.showMessageDialog(null,
				this.getDocumentos().concat("\n").concat(this.getCurso()).concat("\n\n").concat(this.getAprovacao())
						.concat("\n\n").concat(this.getNotas()).concat("\n\n").concat(this.getRecursos()),
				"Aluno: " + this.getNomeCompleto() + " - " + this.getDataDeNascimento(),
				JOptionPane.INFORMATION_MESSAGE);
	}

	private String getAprovacao() {
		String label = new String("Status: ");
		return label.concat(this.candidato.isAprovado() ? "Aprovado" : "Reprovado");
	}

	private String getDocumentos() {
		String layout = new String();
		final int tamanho = this.candidato.getDocumentos().getTamanho();

		for (int i = 0; i < tamanho; ++i) {
			Documento doc = this.candidato.getDocumentos().espiar(i);
			layout = layout.concat(doc.getTitulo()).concat(": ").concat(doc.getNumero()).concat("\n");
		}

		return layout;
	}

	private String getNomeCompleto() {
		return this.candidato.getNome() + " " + this.candidato.getSobrenome();
	}

	private String getCurso() {
		String textoDisplay = new String("Curso: ").concat(this.candidato.getCurso().getNome());

		return textoDisplay;
	}

	private String getRecursos() {
		String labelEtapa = new String("Etapa: ");
		String layout = new String("");
		final int tamanho = this.candidato.getRecursos().getTamanho();

		if (tamanho == 0) {
			layout = new String("Não há recursos");
		}

		for (int i = 0; i < tamanho; ++i) {
			Recurso r = this.candidato.getRecursos().espiar(i);
			layout = layout.concat(labelEtapa.concat(r.getEtapaRecurso().toString())).concat("\n   ")
					.concat(r.getDescricao()).concat("\n");
		}

		return layout;
	}

	private String getDataDeNascimento() {
		return candidato.getDataNascimento().toString();
	}

	private String getNotas() {
		String layout = new String("Não há notas");
		final int tamanho = this.candidato.getNotas().getTamanho();

		for (int i = 0; i < tamanho; ++i) {
			Nota n = this.candidato.getNotas().espiar(0);
			layout = new String("Notas:\n");
			layout = layout.concat(n.toString()).concat(" ");
		}

		return layout;
	}
}
