package edu.fatec.sips.controller;

import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.dao.DAOCPS;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Campus;

public class CPSController {
	ArquivoCampusController arquivoCampusController = new ArquivoCampusController();
	Campus campus = new Campus();
	DAOCPS daocps = new DAOCPS();
	
	public void cadastrarCampus() throws IOException {
		JLabel labelNomeFaculdade = new JLabel("Nome");
		JLabel labelUnidadeFaculdade = new JLabel("Unidade");
		
		JTextField txtNomeFaculdade = new JTextField();
		JTextField txtUnidadeFaculdade = new JTextField();
		
		Object [] options = {labelNomeFaculdade, txtNomeFaculdade, labelUnidadeFaculdade, txtUnidadeFaculdade};
		
		JOptionPane.showMessageDialog(null, options, "CADASTRAR CAMPUS", JOptionPane.PLAIN_MESSAGE);
		
		campus.setId(10);
		campus.setNome(txtNomeFaculdade.getText());
		campus.setUnidade(txtUnidadeFaculdade.getText());
		daocps.salvar(campus);
	}

	public void visualizarCampus() throws IOException {
		ListaLigadaSimples<Campus> listaDeCampus = this.arquivoCampusController.listarCampus();
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
