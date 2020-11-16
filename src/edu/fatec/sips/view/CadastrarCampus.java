package edu.fatec.sips.view;

import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import edu.fatec.sips.controller.CPSController;

public class CadastrarCampus {
	CPSController cpsController = new CPSController();

	public void cadastrarCampus() throws IOException {
		JLabel labelNomeFaculdade = new JLabel("Nome");
		JLabel labelUnidadeFaculdade = new JLabel("Unidade");

		JTextField txtNomeFaculdade = new JTextField();
		JTextField txtUnidadeFaculdade = new JTextField();

		Object[] options = { labelNomeFaculdade, txtNomeFaculdade, labelUnidadeFaculdade, txtUnidadeFaculdade };

		JOptionPane.showMessageDialog(null, options, "CADASTRAR CAMPUS", JOptionPane.PLAIN_MESSAGE);

		cpsController.salvarCampus(txtNomeFaculdade, txtUnidadeFaculdade);
	}
}
