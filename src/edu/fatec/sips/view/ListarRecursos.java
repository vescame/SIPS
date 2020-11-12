package edu.fatec.sips.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.controller.CandidatoController;
import edu.fatec.sips.controller.RecursoController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.data_structure.search.BuscaBinariaRecursos;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Recurso;

public class ListarRecursos {
	private final RecursoController recursoController;
	private final CandidatoController candidatoController;

	private final JTable tabelaRecursos;
	private DefaultTableModel modeloTabela;
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
		tabelaRecursos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evento) {
				selecaoDeLinha(evento);
			}
		});
		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
		defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int numCol = 0; numCol < tabelaRecursos.getColumnCount(); numCol++) {
			for (int i = 0; i <= numCol; i++) {
				tabelaRecursos.getColumnModel().getColumn(i).setPreferredWidth(250);
				tabelaRecursos.getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);
			}
		}
		tabelaRecursos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JOptionPane.showMessageDialog(null, new JScrollPane(tabelaRecursos), "LISTA DE RECURSOS",
				JOptionPane.PLAIN_MESSAGE);
	}

	private void selecaoDeLinha(MouseEvent evento) {
		final int colunaId = 0;
		int indexLinhaSelecionada = tabelaRecursos.getSelectedRow();
		int idSelecionado = (int) this.modeloTabela.getValueAt(indexLinhaSelecionada, colunaId);
		Recurso tmp = buscaBinariaRecurso(idSelecionado);
		int resposta = JOptionPane.showConfirmDialog(null, "Aprovar?\n" + tmp.getDescricao());
		if (resposta != JOptionPane.CANCEL_OPTION) {
			if (resposta == JOptionPane.YES_OPTION) {
				this.recursoController.aprovarRecurso(tmp.getId());
			} else {
				this.recursoController.reprovarRecurso(tmp.getId());
			}
			this.recursos.removerNaPosicao(indexLinhaSelecionada);
			this.modeloTabela.removeRow(indexLinhaSelecionada);
		}
	}

	private Recurso buscaBinariaRecurso(int id) {
		BuscaBinariaRecursos buscaBinariaRecursos = new BuscaBinariaRecursos();
		return buscaBinariaRecursos.buscaBinaria(this.recursos.primeiro, id).getElemento();
	}
}
