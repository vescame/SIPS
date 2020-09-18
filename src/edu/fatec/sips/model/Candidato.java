package edu.fatec.sips.model;

import java.time.LocalDate;

import edu.fatec.sips.data_structure.ListaLigada;
import edu.fatec.sips.data_structure.node.Node;
import edu.fatec.sips.model.base.DocumentoGenerico;

public class Candidato {
	private String nome;
	private String sobrenome;
	private LocalDate dataNascimento;
	private Campus campus;
	private Curso curso;
	private boolean aprovado;
	private ListaLigada<Integer> notas;
	private ListaLigada<DocumentoGenerico> documentos;
	private ListaLigada<Recurso> recursos;

	public Candidato() {
		this.documentos = new ListaLigada<DocumentoGenerico>();
		this.recursos = new ListaLigada<Recurso>();
		this.notas = new ListaLigada<Integer>();
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

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		this.campus = campus;
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
	
	public ListaLigada<Integer> getNotas() {
		return notas;
	}

	public void setNotas(ListaLigada<Integer> notas) {
		this.notas = notas;
	}

	public void setDocumentos(ListaLigada<DocumentoGenerico> documentos) {
		this.documentos = documentos;
	}

	public void addDocumento(DocumentoGenerico documento) {
		documentos.add(new Node<DocumentoGenerico>(documento));
	}

	public ListaLigada<DocumentoGenerico> getDocumentos() {
		return this.documentos;
	}

	public void setRecursos(ListaLigada<Recurso> recursos) {
		this.recursos = recursos;
	}

	public void addRecurso(Recurso recurso) {
		recursos.add(new Node<Recurso>(recurso));
	}

	public ListaLigada<Recurso> getRecursos() {
		return this.recursos;
	}
}
