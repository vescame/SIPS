package edu.fatec.sips.view;

import edu.fatec.sips.controller.ResultadoFinalController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.ResultadoFinal;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class ResultadoFinalView {
	private final ListaLigadaSimples<ResultadoFinal> res;

	public ResultadoFinalView() throws IOException {
		ResultadoFinalController resFinal = new ResultadoFinalController();
		this.res = resFinal.listarResultados();
	}

	public void visualizarResultadoFinal() throws IOException {
		String[] col = { "ID CANDIDATO", "NOME", "EDITAL", "NOTA" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		final int tamanho = this.res.getTamanho();
		for (int i = 0; i < tamanho; i++) {
			final ResultadoFinal res = this.res.espiar(i);
			Object[] edital = {
					res.getCandidato().getId(),
					res.getCandidato().getNome() + " " + res.getCandidato().getSobrenome(),
					res.getCandidato().getEdital().getCurso().getSigla(),
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
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "RESULTADO FINAL", JOptionPane.PLAIN_MESSAGE);
	}
}
