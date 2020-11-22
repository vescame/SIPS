package edu.fatec.sips.view;

import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.controller.CronogramaController;
import edu.fatec.sips.data_structure.FilaImplementacaoDinamica;
import edu.fatec.sips.model.CronogramaDeAtividades;

public class ListarCronograma {

	private CronogramaController cronoController = new CronogramaController();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void visualizarCronograDeAtividades() {
		FilaImplementacaoDinamica<CronogramaDeAtividades> cronograma = cronoController.listarAtividade();
		String[] coluna = {"ID", "DESCRIÇÃO", "INÍCIO", "ENTREGA"};
		DefaultTableModel tableModel = new DefaultTableModel(coluna, 0);
		for(int i = 0; i < cronograma.getTamanho(); i++) {
			Object atividades[] = {cronograma.coletar(i).getIdAtividade(), cronograma.coletar(i).getDescricaoAtividade(), 
			sdf.format(cronograma.coletar(i).getDataInicio()), sdf.format(cronograma.coletar(i).getDataEntrega())};
			tableModel.addRow(atividades);
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "CRONOGRAMA DE ATIVIDADES", JOptionPane.PLAIN_MESSAGE);
	}
	
}
