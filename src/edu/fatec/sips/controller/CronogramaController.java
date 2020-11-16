package edu.fatec.sips.controller;

import java.io.IOException;

import edu.fatec.sips.data_structure.FilaImplementacaoDinamica;
import edu.fatec.sips.file_controller.ArquivoCronogramaController;
import edu.fatec.sips.model.CronogramaDeAtividades;

public class CronogramaController {

	private ArquivoCronogramaController arquivoCronograma;
	private FilaImplementacaoDinamica<CronogramaDeAtividades> filadinamica = new FilaImplementacaoDinamica<CronogramaDeAtividades>();
	
	public CronogramaController() {
		arquivoCronograma = new ArquivoCronogramaController();	
	}
	
	public FilaImplementacaoDinamica<CronogramaDeAtividades> listarAtividade() {
		try {
			return this.arquivoCronograma.listarAtividades();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void gravarAtividade(CronogramaDeAtividades atividade) throws IOException {
		arquivoCronograma.gravar(atividade);
	}
	
	public void visualizarAtividade() throws IOException {
		arquivoCronograma.abrirArquivo();
	}
	
	public void removerAtividade() {
		filadinamica.removerNoInicio();
	}
	
	public String mostrarAtividades() {
		return filadinamica.printFila();
	}	
	
}
