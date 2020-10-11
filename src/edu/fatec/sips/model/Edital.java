package edu.fatec.sips.model;

public class Edital {
	private int id;
	private String titulo;
	private String campus;
	private String curso;
	private String publicoAlvo;
	private String periodoInicial;
	private String periodoFinal;
	private int amplaConcorrencia;
	private int acoesAfirmativas;
	private int deficiente;
	private int criterio;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getPublicoAlvo() {
		return publicoAlvo;
	}
	public void setPublicoAlvo(String publicoAlvo) {
		this.publicoAlvo = publicoAlvo;
	}
	public String getPeriodoInicial() {
		return periodoInicial;
	}
	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}
	public String getPeriodoFinal() {
		return periodoFinal;
	}
	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}
	public int getAmplaConcorrencia() {
		return amplaConcorrencia;
	}
	public void setAmplaConcorrencia(int amplaConcorrencia) {
		this.amplaConcorrencia = amplaConcorrencia;
	}
	public int getAcoesAfirmativas() {
		return acoesAfirmativas;
	}
	public void setAcoesAfirmativas(int acoesAfirmativas) {
		this.acoesAfirmativas = acoesAfirmativas;
	}
	public int getDeficiente() {
		return deficiente;
	}
	public void setDeficiente(int deficiente) {
		this.deficiente = deficiente;
	}
	public int getCriterio() {
		return criterio;
	}
	public void setCriterio(int criterio) {
		this.criterio = criterio;
	}
		
}
