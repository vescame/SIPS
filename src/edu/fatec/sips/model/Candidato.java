package edu.fatec.sips.model;

import java.time.LocalDate;

import edu.fatec.sips.data_structure.ListaLigadaSimples;

public class Candidato {
	private int id;
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private Curso curso;
	private boolean aprovado;
	private ListaLigadaSimples<Nota> notas;
	private ListaLigadaSimples<Documento> documentos;
	private ListaLigadaSimples<Recurso> recursos;

	public Candidato() {
		this.documentos = new ListaLigadaSimples<Documento>();
		this.recursos = new ListaLigadaSimples<Recurso>();
		this.notas = new ListaLigadaSimples<Nota>();
	}

	public Candidato(int id, String nome, String sobrenome, LocalDate dataNascimento, Curso curso, boolean aprovado,
			ListaLigadaSimples<Nota> notas, ListaLigadaSimples<Documento> documentos,
			ListaLigadaSimples<Recurso> recursos) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.curso = curso;
		this.aprovado = aprovado;
		this.notas = new ListaLigadaSimples<Nota>();
		this.documentos = new ListaLigadaSimples<Documento>();
		this.recursos = new ListaLigadaSimples<Recurso>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public ListaLigadaSimples<Nota> getNotas() {
		return notas;
	}

	public void addNota(Nota nota) {
		this.notas.adicionar(nota);
	}

	public void setNotas(ListaLigadaSimples<Nota> notas) {
		this.notas = notas;
	}

	public void setDocumentos(ListaLigadaSimples<Documento> documentos) {
		this.documentos = documentos;
	}

	public void addDocumento(Documento documento) {
		documentos.adicionar(documento);
	}

	public ListaLigadaSimples<Documento> getDocumentos() {
		return this.documentos;
	}

	public void setRecursos(ListaLigadaSimples<Recurso> recursos) {
		this.recursos = recursos;
	}

	public void addRecurso(Recurso recurso) {
		recursos.adicionar(recurso);
	}

	public ListaLigadaSimples<Recurso> getRecursos() {
		return this.recursos;
	}

	@Override
	public String toString() {
		return "Candidato\nid=" + id + "\nnome=" + nome + " " + sobrenome + "\ndataNascimento="
				+ dataNascimento + "\n" + (aprovado ? "aprovado" : "reprovado") + "";
	}
	
	
}
