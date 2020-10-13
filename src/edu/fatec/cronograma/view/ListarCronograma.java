package edu.fatec.cronograma.view;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
		for(int i = 0; i < cronograma.getSize(); i++) {
			Object atividades[] = {cronograma.coletar(i).getIdAtividade(), cronograma.coletar(i).getDescricaoAtividade(), 
			sdf.format(cronograma.coletar(i).getDataInicio()), sdf.format(cronograma.coletar(i).getDataEntrega())};
			tableModel.addRow(atividades);
		}
		JTable tabela = new JTable(tableModel);
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JOptionPane.showMessageDialog(null, new JScrollPane(tabela), "CRONOGRAMA DE ATIVIDADES", JOptionPane.PLAIN_MESSAGE);
	}
	
}
