package edu.fatec.cronograma.view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import edu.fatec.sips.controller.CronogramaController;
import edu.fatec.sips.model.CronogramaDeAtividades;

public class CadastrarCronograma {

	CronogramaController cronoController = new CronogramaController();

	public void cadastrarCronogramaDeAtividades() throws ParseException, IOException {
		CronogramaDeAtividades cronogramaDeAtividades = new CronogramaDeAtividades();
		JLabel labelDescricaoDeAtividades = new JLabel("Descrição");
		JTextField txtDescricaoDaAtividade = new JTextField();
		
		JLabel labelData = new JLabel("Data");
		JFormattedTextField txtDataDeInicio = new JFormattedTextField(criarMascaraDeEntrada("##/##/####"));
		txtDataDeInicio.setHorizontalAlignment(JTextField.CENTER);
		txtDataDeInicio.setColumns(6);
		JLabel labelIconeDataDeInicio = new JLabel(new ImageIcon("icon-calendar.png"));
		JFormattedTextField txtDataDeEntrega = new JFormattedTextField(criarMascaraDeEntrada("##/##/####"));
		txtDataDeEntrega.setHorizontalAlignment(JTextField.CENTER);
		txtDataDeEntrega.setColumns(6);
		JLabel labelIconeDataDeEntrega = new JLabel(new ImageIcon("icon-calendar.png"));
		
		JPanel painelDeDatas = new JPanel();
		painelDeDatas.add(new JLabel("Ínicio"));
		painelDeDatas.add(txtDataDeInicio);
		painelDeDatas.add(labelIconeDataDeInicio);
		painelDeDatas.add(new JLabel("Entrega"));
		painelDeDatas.add(txtDataDeEntrega);
		painelDeDatas.add(labelIconeDataDeEntrega);
		
		Object[] componentes = { labelDescricaoDeAtividades, txtDescricaoDaAtividade, labelData, painelDeDatas};

		JOptionPane.showMessageDialog(null, componentes, "CADASTRAR CRONOGRAMA", JOptionPane.PLAIN_MESSAGE);

		cronogramaDeAtividades.setDescricaoAtividade(txtDescricaoDaAtividade.getText());
		cronogramaDeAtividades.setDataInicio(formatarDataPtBr(txtDataDeInicio.getText()));
		cronogramaDeAtividades.setDataEntrega(formatarDataPtBr(txtDataDeEntrega.getText()));
		cronoController.gravarAtividade(cronogramaDeAtividades);
		cronoController.visualizarAtividade();

	}

	public Date formatarDataPtBr(String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada;
		dataFormatada = sdf.parse(data);
		return dataFormatada;
	}
	
	public MaskFormatter criarMascaraDeEntrada(String formatoDeMascara) {

		MaskFormatter F_Mascara = new MaskFormatter();
		try {
			F_Mascara.setMask(formatoDeMascara); // Atribui a mascara
			F_Mascara.setPlaceholderCharacter(' '); // Caracter para preencimento
		} catch (Exception excecao) {
			excecao.printStackTrace();
		}
		return F_Mascara;
	}

}
