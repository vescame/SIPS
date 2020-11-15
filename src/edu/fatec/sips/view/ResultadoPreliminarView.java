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
		for (int i = 0; i < listaDeResultadoPrelimar.getTamanho(); i++) {
			Object[] edital = { listaDeResultadoPrelimar.espiar(i).getCandidato().getId(),
					listaDeResultadoPrelimar.espiar(i).getCandidato().getNome(),
					listaDeResultadoPrelimar.espiar(i).getCandidato().getSobrenome(),
					listaDeResultadoPrelimar.espiar(i).getCandidato().getEdital().getId(),
					listaDeResultadoPrelimar.espiar(i).getCandidato().getEdital().getCurso().getSigla() + " - "
							+ listaDeResultadoPrelimar.espiar(i).getCandidato().getEdital().getCurso().getNome(),
					listaDeResultadoPrelimar.espiar(i).getCandidato().getNota() };
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
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "LISTA DE EDITAL", JOptionPane.PLAIN_MESSAGE);
	}
}
