package edu.fatec.sips.controller;

import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.file_controller.ArquivoCampusController;
import edu.fatec.sips.file_controller.ArquivoCursoController;
import edu.fatec.sips.file_controller.ArquivoEditalController;
import edu.fatec.sips.model.Campus;
import edu.fatec.sips.model.Curso;
import edu.fatec.sips.model.Edital;
import edu.fatec.sips.view.ListarCandidatos;

public class CampusController {
	private final ArquivoCampusController bdCampus;
	private final ArquivoCursoController bdCurso;
	private final ArquivoEditalController bdEdital;

	Edital edital = new Edital();
	EditalController editalController = new EditalController();

	public CampusController() {
		this.bdCampus = new ArquivoCampusController();
		this.bdEdital = new ArquivoEditalController();
		this.bdCurso = new ArquivoCursoController();
	}

	ListarCandidatos listarCandidatos = new ListarCandidatos();

	public Campus getPorId(int id) {
		Campus encontrado = null;

		try {
			encontrado = this.bdCampus.buscarPorId(id);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (encontrado == null) {
				System.out.println("campus de id " + id + " nao encontrado");
			}
		}

		return encontrado;
	}
	
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
	
	public ListaLigadaSimples<Campus> listarCampus(){
		try {
			return this.bdCampus.listarCampus();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ListaLigadaSimples<Curso> listarCursos(){
		try {
			return this.bdCurso.listarCursos();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void salvarEdital(JTextField txtTitulo, Campus campus, Curso curso,
			JTextField txtPublicoAlvo, JTextField txtPeriodoInicial, JTextField txtPeriodoFinal,
			JFormattedTextField txtQtdVagasAmplaConcorrencia, JFormattedTextField txtQtdVagasAcoesAfirmativas,
			JFormattedTextField txtQtdVagasDeficiente, JComboBox<String> comboBoxCriterio) throws IOException {
		edital.setTitulo(txtTitulo.getText());
		edital.setCampus(campus);
		edital.setCurso(curso);
		edital.setPublicoAlvo(txtPublicoAlvo.getText());
		edital.setPeriodoInicial(txtPeriodoInicial.getText());
		edital.setPeriodoFinal(txtPeriodoFinal.getText());
		edital.setAmplaConcorrencia(Integer.parseInt(txtQtdVagasAmplaConcorrencia.getText()));
		edital.setAcoesAfirmativas(Integer.parseInt(txtQtdVagasAcoesAfirmativas.getText()));
		edital.setDeficiente(Integer.parseInt(txtQtdVagasDeficiente.getText()));
		edital.setCriterio(Integer.parseInt(comboBoxCriterio.getSelectedItem().toString()));

		bdEdital.gravarEdital(edital);

	}
	
	public void atualizarEdital(JTextField txtTitulo, Campus campus, Curso curso,
			JTextField txtPublicoAlvo, JTextField txtPeriodoInicial, JTextField txtPeriodoFinal,
			JFormattedTextField txtQtdVagasAmplaConcorrencia, JFormattedTextField txtQtdVagasAcoesAfirmativas,
			JFormattedTextField txtQtdVagasDeficiente, JComboBox<String> comboBoxCriterio) throws Exception {
		edital.setTitulo(txtTitulo.getText());
		edital.setCampus(campus);
		edital.setCurso(curso);
		edital.setPublicoAlvo(txtPublicoAlvo.getText());
		edital.setPeriodoInicial(txtPeriodoInicial.getText());
		edital.setPeriodoFinal(txtPeriodoFinal.getText());
		edital.setAmplaConcorrencia(Integer.parseInt(txtQtdVagasAmplaConcorrencia.getText()));
		edital.setAcoesAfirmativas(Integer.parseInt(txtQtdVagasAcoesAfirmativas.getText()));
		edital.setDeficiente(Integer.parseInt(txtQtdVagasDeficiente.getText()));
		edital.setCriterio(Integer.parseInt(comboBoxCriterio.getSelectedItem().toString()));

		bdEdital.atualizarEdital(edital);;

	}

	public ListaLigadaSimples<Edital> retornarListaDeEditais()
			throws IOException {
		ListaLigadaSimples<Edital> listaDeEdital = this.bdEdital.listarEditais();
		return listaDeEdital;
	}
	
	public ListaLigadaSimples<Campus> retornarListaDeCampus()
			throws IOException {
		ListaLigadaSimples<Campus> listaDeCampus = this.bdCampus.listarCampus();
		return listaDeCampus;
	}
	
	public ListaLigadaSimples<Curso> retornarListaDeCurso()
			throws IOException {
		ListaLigadaSimples<Curso> listaDeCursos = this.bdCurso.listarCursos();
		return listaDeCursos;
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
