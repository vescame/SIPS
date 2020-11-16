package edu.fatec.sips.controller;

import java.io.IOException;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.data_structure.sorting.QuickSortResultadoFinal;
import edu.fatec.sips.file_controller.ArquivoCursoController;
import edu.fatec.sips.file_controller.ArquivoEditalController;
import edu.fatec.sips.file_controller.ArquivoResultadoFinalController;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Curso;
import edu.fatec.sips.model.Edital;
import edu.fatec.sips.model.ResultadoFinal;


public class ResultadoFinalController {
	private ArquivoResultadoFinalController bdResFinal;
	private ArquivoEditalController ctrlEdital;
	private ListaLigadaSimples<Candidato> candidatos;
	private ListaLigadaSimples<Edital> editais;
	private ListaLigadaSimples<Curso> cursos;
	private ListaLigadaSimples<ResultadoPreliminarController> resultadoPreliminar;
	
	public ResultadoFinalController() {
		try {
			this.bdResFinal = new ArquivoResultadoFinalController();
			this.ctrlEdital = new ArquivoEditalController();
			this.candidatos = new CandidatoController().listarCandidatosOrdenados();
			this.cursos = new ArquivoCursoController().listarCursos();
			this.editais = new ArquivoEditalController().listarEditais();
			this.resultadoPreliminar = new ListaLigadaSimples<ResultadoPreliminarController>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ResultadoFinal getPorCandidato(final int idCandidato) {
		ResultadoFinal rs = new ResultadoFinal();
		
		try {
			ListaLigadaSimples<ResultadoFinal> res = bdResFinal.listarResultado();
			final int tamanho = res.getTamanho();
			ResultadoFinal[] vetor = new ResultadoFinal[tamanho];
			
			for (int i = 0; i < tamanho; ++i) {
				vetor[i] = res.espiar(i);
			}
			
			QuickSortResultadoFinal qsResFinal = new QuickSortResultadoFinal();
			vetor = qsResFinal.sort(vetor, 0, tamanho - 1);
			
			System.out.println(vetor);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rs;
	}

	public ListaLigadaSimples<ResultadoFinal> listarResultado() {
		ListaLigadaSimples<ResultadoFinal> res = null;
		
		try {
			res = bdResFinal.listarResultado();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public void iniciarAprovacao() {
		
	}
}
