package edu.fatec.sips.model;

import java.time.LocalDate;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.base.DocumentoGenerico;

public class Candidato {
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private Curso curso;
	private boolean aprovado;
	private ListaLigadaSimples<Nota> notas;
	private ListaLigadaSimples<DocumentoGenerico> documentos;
	private ListaLigadaSimples<Recurso> recursos;

	public Candidato() {
		this.documentos = new ListaLigadaSimples<DocumentoGenerico>();
		this.recursos = new ListaLigadaSimples<Recurso>();
		this.notas = new ListaLigadaSimples<Nota>();
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
		this.notas.add(nota);
	}

	public void setNotas(ListaLigadaSimples<Nota> notas) {
		this.notas = notas;
	}

	public void setDocumentos(ListaLigadaSimples<DocumentoGenerico> documentos) {
		this.documentos = documentos;
	}

	public void addDocumento(DocumentoGenerico documento) {
		documentos.add(documento);
	}

	public ListaLigadaSimples<DocumentoGenerico> getDocumentos() {
		return this.documentos;
	}

	public void setRecursos(ListaLigadaSimples<Recurso> recursos) {
		this.recursos = recursos;
	}

	public void addRecurso(Recurso recurso) {
		recursos.add(recurso);
	}

	public ListaLigadaSimples<Recurso> getRecursos() {
		return this.recursos;
	}
}
