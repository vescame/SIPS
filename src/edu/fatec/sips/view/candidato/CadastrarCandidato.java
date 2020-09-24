package edu.fatec.sips.view.candidato;

import java.time.LocalDate;

import javax.swing.JOptionPane;

import edu.fatec.sips.controller.CandidatoController;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Curso;

public class CadastrarCandidato {
	CandidatoController candidatoController = new CandidatoController();

	public void cadastrar() {
		Candidato candidato = new Candidato();
		candidato.setNome(JOptionPane.showInputDialog("Nome do candidato:"));
		candidato.setSobrenome(JOptionPane.showInputDialog("Sobrenome do candidato:"));
		
		LocalDate dataNascimento = null;
		boolean dataValida = false;
		while (!dataValida) {
			try {
				dataNascimento = LocalDate.parse(JOptionPane.showInputDialog("data nascimento:"));
				dataValida = true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Data de Nascimento Invalida, formato é yyyy-MM-dd");
			}
		}
		candidato.setDataNascimento(dataNascimento);
		candidato.setAprovado(Boolean.valueOf(JOptionPane.showInputDialog("Aprovado? true : false:")));
		candidato.setCurso(new Curso(5, "ffd", "curso qualquer"));
		candidatoController.salvar(candidato);
	}
}
