package edu.fatec.sips.view.candidato;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.fatec.sips.controller.CandidatoController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;

public class ListarCandidatos {
	private final JTable tabelaUsuarios;
	private final DefaultTableModel modeloTabela;
	private final String colunas[] = { "ID", "NOME", "SOBRENOME", "DOCUMENTOS", "DT. NASCIMENTO", "CURSO", "NOTA",
			"RECURSO" };
	CandidatoController candidatoController = new CandidatoController();
	private final ListaLigadaSimples<Candidato> candidatos; 

	public ListarCandidatos() {
		this.modeloTabela = new DefaultTableModel(colunas, 0);
		this.tabelaUsuarios = new JTable(modeloTabela);
		candidatos = candidatoController.listarCandidatos();
		for (int i = 0; i < candidatos.getTamanho(); ++i) {
			Object[] candidatosTable = { candidatos.espiar(i).getId(), candidatos.espiar(i).getNome(),
					candidatos.espiar(i).getSobrenome(), candidatos.espiar(i).getDocumentos(),
					candidatos.espiar(i).getDataNascimentoString(), candidatos.espiar(i).getCurso(),
					candidatos.espiar(i).getNotas(), candidatos.espiar(i).getRecursos() };
			modeloTabela.addRow(candidatosTable);
		}
	}

	public void listar() {
		tabelaUsuarios.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evento) {
            	selecaoDeLinha(evento);
            }
        });
		
		tabelaUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JOptionPane.showMessageDialog(null, new JScrollPane(tabelaUsuarios), "LISTA DE CANDIDATOS", JOptionPane.PLAIN_MESSAGE);
	}

	private void selecaoDeLinha(java.awt.event.MouseEvent evt) {
		final int colunaId = 0;
		int indexLinhaSelecionada = tabelaUsuarios.getSelectedRow();
		int idSelecionado = (int) this.modeloTabela.getValueAt(indexLinhaSelecionada, colunaId);
		Candidato candidatoSelecionado = this.candidatoController.getPorId(idSelecionado);
		new VisualizarCandidato(candidatoSelecionado).mostrarTela();
	}
}