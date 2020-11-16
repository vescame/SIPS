package edu.fatec.sips.view;

import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.controller.ResultadoPreliminarController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.ResultadoPreliminar;

public class ResultadoPreliminarView {
	private ResultadoPreliminarController resultadoPreliminarController = new ResultadoPreliminarController();

	public void definirResultadoPreliminar() throws IOException {
		resultadoPreliminarController.definirResultadoPreliminar();
	}

	public void visualizarResultadoPreliminar() throws IOException {
		ListaLigadaSimples<ResultadoPreliminar> listaDeResultadoPrelimar = resultadoPreliminarController
				.listarResultadoPreliminar();
		String col[] = { "ID CANDIDATO", "NOME CANDIDATO", "SOBRENOME CANDIDATO", "ID EDITAL", "CURSO EDITAL",
				"NOTA CANDIDATO" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		final int tamanho = listaDeResultadoPrelimar.getTamanho();
		for (int i = 0; i < tamanho; i++) {
			final ResultadoPreliminar res = listaDeResultadoPrelimar.espiar(i);
			Object[] edital = {
					res.getCandidato().getId(),
					res.getCandidato().getNome(),
					res.getCandidato().getSobrenome(),
					res.getCandidato().getEdital().getId(),
					res.getCandidato().getEdital().getCurso().getSigla() + " - " +
					res.getCandidato().getEdital().getCurso().getNome(),
					res.getCandidato().getNota()
			};
			tableModel.addRow(edital);
		}
		JTable table = new JTable(tableModel);
		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
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
