package edu.fatec.sips.view.candidato;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.controller.CandidatoController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;

public class ListarCandidatos {
	CandidatoController candidatoController = new CandidatoController();

	public void listar() {
		final ListaLigadaSimples<Candidato> candidatos = candidatoController.listarCandidatos();
		String col[] = { "ID", "NOME", "Sobrenome", "Documentos", "Data de nascimento", "Curso", "Nota", "Recurso"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		for (int i = 0; i < candidatos.getTamanho(); ++i) {
			Object[] candidatosTable = {
						candidatos.espiar(i).getId(),
						candidatos.espiar(i).getNome(),
						candidatos.espiar(i).getSobrenome(),
						candidatos.espiar(i).getDocumentos().espiar(0),
						candidatos.espiar(i).getDataNascimentoString(),
						candidatos.espiar(i).getCurso(),
						candidatos.espiar(i).getNotas(),
						candidatos.espiar(i).getRecursos()
					};
			tableModel.addRow(candidatosTable);
		}
		JTable table = new JTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JOptionPane.showMessageDialog(null, new JScrollPane(table), "LISTA DE CANDIDATOS", JOptionPane.PLAIN_MESSAGE);
	}
}