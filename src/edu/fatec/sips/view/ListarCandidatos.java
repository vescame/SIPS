package edu.fatec.sips.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.controller.CandidatoController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.data_structure.sorting.MergeSortCandidatos;
import edu.fatec.sips.model.Candidato;

public class ListarCandidatos {
	private final JTable tabelaUsuarios;
	private final DefaultTableModel modeloTabela;
	private final String colunas[] = { "ID", "NOME", "SOBRENOME", "DOCUMENTOS", "DT. NASCIMENTO", "CURSO" };
	private final CandidatoController candidatoController = new CandidatoController();
	private final MergeSortCandidatos candidatos;

	public ListarCandidatos() {
		this.modeloTabela = new DefaultTableModel(colunas, 0);
		this.tabelaUsuarios = new JTable(modeloTabela);

		candidatos = candidatoController.listarCandidatosOrdenados();

		try {
			for (int i = 0; i < candidatos.getTamanho(); ++i) {
				Candidato c = candidatos.espiar(i);
				Object[] candidato = { c.getId(), c.getNome(), c.getSobrenome(), c.getDocumentos().espiar(0),
						c.getDataNascimentoString(), c.getCurso(), c.getNota(), c.getRecursos() };
				modeloTabela.addRow(candidato);
			}
		} catch (NullPointerException e) {
			System.out.println("FALHA AO ADICIONAR CANDIDATO A LISTA");
		}
	}

	public void listar() {
		tabelaUsuarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evento) {
				selecaoDeLinha(evento);
			}
		});

		tabelaUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JOptionPane.showMessageDialog(null, new JScrollPane(tabelaUsuarios), "LISTA DE CANDIDATOS",
				JOptionPane.PLAIN_MESSAGE);
	}

	private void selecaoDeLinha(MouseEvent evt) {
		final int colunaId = 0;
		int indexLinhaSelecionada = tabelaUsuarios.getSelectedRow();
		int idSelecionado = (int) this.modeloTabela.getValueAt(indexLinhaSelecionada, colunaId);
		Candidato candidatoSelecionado = this.candidatoController.getPorId(idSelecionado);
		new VisualizarCandidato(candidatoSelecionado).mostrarTela();
	}
}