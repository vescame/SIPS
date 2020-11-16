package edu.fatec.sips.view;

import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.controller.EditalController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Edital;

public class VisualizarEdital {
	EditalController editalController = new EditalController();

	public void visualizarEdital() throws IOException {
		ListaLigadaSimples<Edital> listaDeEdital = editalController.listarEditais();
		String col[] = { "ID", "TÍTULO", "CAMPUS", "CURSO", "PÚBLICO ALVO", "PERÍODO INICIAL", "PERÍODO FINAL",
				"QTD. VAGAS AMPLA CONCORRÊNCIA", "QTD. VAGAS AÇÕES AFIRMATIVAS", "QTD. VAGAS DEFICIENTE", "CRITÉRIO" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < listaDeEdital.getTamanho(); i++) {
			Object[] edital = { listaDeEdital.espiar(i).getId(), listaDeEdital.espiar(i).getTitulo(),
					listaDeEdital.espiar(i).getCampus(), listaDeEdital.espiar(i).getCurso(),
					listaDeEdital.espiar(i).getPublicoAlvo(), listaDeEdital.espiar(i).getPeriodoInicial(),
					listaDeEdital.espiar(i).getPeriodoFinal(), listaDeEdital.espiar(i).getAmplaConcorrencia(),
					listaDeEdital.espiar(i).getAcoesAfirmativas(), listaDeEdital.espiar(i).getDeficiente(),
					listaDeEdital.espiar(i).getCriterio() };
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
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "LISTA DE EDITAIS", JOptionPane.PLAIN_MESSAGE);
	}
}
