package edu.fatec.sips.model;

public class Edital {
	private int id;
	private String titulo;
	private Campus campus;
	private Curso curso;
	private Vaga vaga;
	private Documento documento;

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
	public Campus getCampus() {
		return campus;
	}
	public void setCampus(Campus campus) {
		this.campus = campus;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Vaga getVaga() {
		return vaga;
	}
	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
	public Documento getDocumento() {
		return documento;
	}
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	@Override
	public String toString() {
		return "Edital [id=" + id + ", titulo=" + titulo + ", campus=" + campus + ", curso=" + curso + ", vaga=" + vaga
				+ ", documento=" + documento + "]";
	}
}
