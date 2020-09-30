package edu.fatec.sips.controller;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Campus;

public class CPSController {
	ListaLigadaSimples<Campus> listaDeCampus = new ListaLigadaSimples<Campus>();

	public void cadastrarCampus(int numeroDeCampus) {
		for (int i = 1; i <= numeroDeCampus; i++) {
			Campus campus = new Campus(i, "Fatec", "Leste " + i);
			listaDeCampus.adicionar(campus);
		}
	}

	public void visualizarCampus() {
		String col[] = { "ID", "NOME", "UNIDADE"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < listaDeCampus.getTamanho(); i++) {
			Object[] campus = { listaDeCampus.espiar(i).getId(), listaDeCampus.espiar(i).getNome(),
					listaDeCampus.espiar(i).getUnidade() };
			tableModel.addRow(campus);
		}
		JTable table = new JTable(tableModel);
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "LISTA DE CAMPUS", JOptionPane.PLAIN_MESSAGE);
	}
}
