package edu.fatec.sips.view.candidato;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.controller.CandidatoController;
import edu.fatec.sips.controller.RecursoController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Recurso;

public class ListarRecursos {
	private final RecursoController recursoController;
	private final CandidatoController candidatoController;

	private final JTable tabelaRecursos;
	private final DefaultTableModel modeloTabela;
	private final String colunas[] = { "ID", "CANDIDATO", "ETAPA" };
	private final ListaLigadaSimples<Recurso> recursos;

	public ListarRecursos() {
		this.recursoController = new RecursoController();
		this.candidatoController = new CandidatoController();

		this.modeloTabela = new DefaultTableModel(colunas, 0);
		this.tabelaRecursos = new JTable(modeloTabela);

		this.recursos = recursoController.listarRecursosAbertos();

		for (int i = 0; i < recursos.getTamanho(); ++i) {
			Recurso tmpRecurso = recursos.espiar(i);
			Candidato tmpCandidato = candidatoController.getPorId(tmpRecurso.getIdCandidato());
			Object[] camposRecurso = { tmpRecurso.getId(), tmpCandidato.getNome(), tmpRecurso.getEtapaRecurso() };
			modeloTabela.addRow(camposRecurso);
		}
	}

	public void listar() {
		tabelaRecursos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int resposta = JOptionPane.showConfirmDialog(null, new JScrollPane(tabelaRecursos), "Iniciar Aprovação de Recursos?",
				JOptionPane.YES_NO_OPTION);
		
		int iterar = this.recursos.getTamanho();
		if (resposta == JOptionPane.YES_OPTION) {
		    for (int i = 0; i < iterar; ++i) {
		    	Recurso tmp = this.recursos.espiar(i);
		    	this.recursoController.aprovacaoAutomatizada(tmp.getId());
		    }
		}
	}

	
}
