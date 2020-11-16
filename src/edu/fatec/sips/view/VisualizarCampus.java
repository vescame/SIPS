package edu.fatec.sips.view;

import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.controller.CPSController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Campus;

public class VisualizarCampus {
	CPSController cpsController = new CPSController();

	public void visualizarCampus() throws IOException {
		ListaLigadaSimples<Campus> listaDeCampus = cpsController.listarCampus();
		String col[] = { "ID", "NOME", "UNIDADE" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < listaDeCampus.getTamanho(); i++) {
			Object[] campus = { listaDeCampus.espiar(i).getId(), listaDeCampus.espiar(i).getNome(),
					listaDeCampus.espiar(i).getUnidade() };
			tableModel.addRow(campus);
		}
		JTable table = new JTable(tableModel);
		DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
		rendar.setHorizontalAlignment(JLabel.CENTER);
		for (int numCol = 0; numCol < table.getColumnCount(); numCol++) {
			for (int i = 0; i <= numCol; i++) {
				table.getColumnModel().getColumn(i).setPreferredWidth(250);
				table.getColumnModel().getColumn(i).setCellRenderer(rendar);
			}
		}
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "LISTA DE CAMPUS", JOptionPane.PLAIN_MESSAGE);
	}
}
