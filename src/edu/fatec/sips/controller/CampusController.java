package edu.fatec.sips.controller;

import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import edu.fatec.sips.dao.DAOEDITAL;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Edital;
import edu.fatec.sips.view.candidato.ListarCandidatos;

public class CampusController {
	private final ArquivoEditalController bdEdital;

	Edital edital = new Edital();
	DAOEDITAL daoedital = new DAOEDITAL();

	public CampusController() {
		this.bdEdital = new ArquivoEditalController();
	}

	ListarCandidatos listarCandidatos = new ListarCandidatos();

	public void listarCandidatos() {
		listarCandidatos.listar();
	}

	public ListaLigadaSimples<Edital> listarEditais() {
		try {
			return this.bdEdital.listarEditais();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void salvarEdital(JTextField txtTitulo, JComboBox<String> comboBoxCampus, JComboBox<String> comboBoxCursos,
			JTextField txtPublicoAlvo, JFormattedTextField txtPeriodoInicial, JFormattedTextField txtPeriodoFinal,
			JFormattedTextField txtQtdVagasAmplaConcorrencia, JFormattedTextField txtQtdVagasAcoesAfirmativas,
			JFormattedTextField txtQtdVagasDeficiente, JComboBox<String> comboBoxCriterio) throws IOException {
		edital.setTitulo(txtTitulo.getText());
		edital.setCampus(comboBoxCampus.getSelectedItem().toString());
		edital.setCurso(comboBoxCursos.getSelectedItem().toString());
		edital.setPublicoAlvo(txtPublicoAlvo.getText());
		edital.setPeriodoInicial(txtPeriodoInicial.getText());
		edital.setPeriodoFinal(txtPeriodoFinal.getText());
		edital.setAmplaConcorrencia(Integer.parseInt(txtQtdVagasAmplaConcorrencia.getText()));
		edital.setAcoesAfirmativas(Integer.parseInt(txtQtdVagasAcoesAfirmativas.getText()));
		edital.setAcoesAfirmativas(Integer.parseInt(txtQtdVagasDeficiente.getText()));
		edital.setCriterio(Integer.parseInt(comboBoxCriterio.getSelectedItem().toString()));

		bdEdital.gravarEdital(edital);

	}

	public ListaLigadaSimples<Edital> retornarListaDeEditais()
			throws IOException {
		ListaLigadaSimples<Edital> listaDeEdital = this.bdEdital.listarEditais();
		return listaDeEdital;
	}

	public void salvar(final Edital edital) {
		try {
			this.bdEdital.gravarEdital(edital);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void atualizar(final Edital edital) {
		try {
			this.bdEdital.atualizarEdital(edital);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Edital remover(final Edital edital) {
		Edital removido = null;

		try {
			removido = this.bdEdital.removerEdital(edital);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (removido == null) {
				System.out.println("edital de id " + edital.getId() + " nao existe");
			}
		}

		return removido;
	}
}
