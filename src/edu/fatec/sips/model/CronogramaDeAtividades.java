package edu.fatec.sips.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CronogramaDeAtividades {

	private int idAtividade; 
	private Date dataInicio = new Date(), dataEntrega = new Date();
	private String descricaoAtividade;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  
	public int getIdAtividade() {
		return idAtividade;
	}
	public void setIdAtividade(int idAtividade) {
		this.idAtividade = idAtividade;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public String getDescricaoAtividade() {
		return descricaoAtividade;
	}
	public void setDescricaoAtividade(String descricaoAtividade) {
		this.descricaoAtividade = descricaoAtividade;
	} 

	@Override
	public String toString() {
		String retorno = "\n---------------------------------------------- "
				       + "\nAtividade: " + getIdAtividade() + 
				         "\nDescrição: " + getDescricaoAtividade() + 
				         "\nInício: " + sdf.format(getDataInicio()) +
				         "\nTérmino: " + sdf.format(getDataEntrega()) + "\n";
		return retorno;
	}
	
	
}
