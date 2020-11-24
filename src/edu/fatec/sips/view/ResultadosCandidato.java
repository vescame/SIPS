package edu.fatec.sips.view;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.controller.CandidatoController;
import edu.fatec.sips.controller.DocumentoCandidatoController;
import edu.fatec.sips.controller.ResultadoFinalController;
import edu.fatec.sips.controller.ResultadoPreliminarController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.enums.Etapa;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Documento;
import edu.fatec.sips.model.ResultadoFinal;
import edu.fatec.sips.model.ResultadoPreliminar;

public class ResultadosCandidato {
	private final DocumentoCandidatoController ctrlDocumentosCandidato;
	private final ResultadoPreliminarController resultadoPreliminarController;
	private final CandidatoController ctrlCandidatos;

	public ResultadosCandidato(final Etapa etapa) {
		this.ctrlDocumentosCandidato = new DocumentoCandidatoController();
		this.ctrlCandidatos = new CandidatoController();
		this.resultadoPreliminarController = new ResultadoPreliminarController();

		try {
			final Candidato candidato = this.escolherCandidato(null);
			if (etapa == Etapa.RESULTADO_FINAL) {
				ResultadoFinal resFinal = new ResultadoFinalController().getPorCandidato(candidato.getId());
				boolean resultado = resFinal != null;
				visualizarResultadoFinal(resultado);
			} else if (etapa == Etapa.RESULTADO_PRELIMINAR) {
				try {
					this.visualizarResultadoPreliminar(candidato.getId());
				} catch (IOException e) {
					e.printStackTrace();
				}
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

	private void visualizarResultadoFinal(final boolean aprovado) {
		String mensagem = aprovado ? "Você está aprovado!" : "Você não foi aprovado dessa vez.";
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public void visualizarResultadoPreliminar(int id) throws IOException {
		ListaLigadaSimples<ResultadoPreliminar> listaDeResultadoPrelimar = resultadoPreliminarController
				.listarResultadoPreliminar();
		String col[] = { "ID CANDIDATO", "NOME CANDIDATO", "SOBRENOME CANDIDATO", "ID EDITAL", "CURSO EDITAL",
				"NOTA CANDIDATO" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		final int tamanho = listaDeResultadoPrelimar.getTamanho();
		for (int i = 0; i < tamanho; i++) {
			final ResultadoPreliminar res = listaDeResultadoPrelimar.espiar(i);
			Object[] edital = { res.getCandidato().getId(), res.getCandidato().getNome(),
					res.getCandidato().getSobrenome(), res.getCandidato().getEdital().getId(),
					res.getCandidato().getEdital().getCurso().getSigla() + " - "
							+ res.getCandidato().getEdital().getCurso().getNome(),
					res.getCandidato().getNota() };
			tableModel.addRow(edital);
		}
		JTable table = new JTable(tableModel);
		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer() {

			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);

				int rid = Integer.valueOf(table.getValueAt(row, 0).toString());
				if (rid == id) {
					label.setBackground(Color.GREEN);
				} else {
					label.setBackground(Color.WHITE);
				}

				return label;
			}

			private static final long serialVersionUID = 1L;
		};

		defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int numCol = 0; numCol < table.getColumnCount(); numCol++) {
			for (int i = 0; i <= numCol; i++) {
				table.getColumnModel().getColumn(i).setPreferredWidth(250);
				table.getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);
			}
		}

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "RESULTADO PRELIMINAR", JOptionPane.PLAIN_MESSAGE);
	}
}
