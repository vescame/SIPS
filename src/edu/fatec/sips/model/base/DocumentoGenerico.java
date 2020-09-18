package edu.fatec.sips.model.base;

public abstract class DocumentoGenerico {
	protected String nome;
	protected String identificador;
	
	/**
	* Cria um documento gen�rico como por exemplo CPF, RG,
	* Carta de Habilita��o etc. 
	*
	* @param  nome  nome do documento
	* @param  identificacao n�mero identificador do documento
	* 
	*/
	protected DocumentoGenerico(String nome, String identificacao) {
		this.nome = nome;
		this.identificador = identificacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	* @return a vari�vel identifica��o que cont�m o id do documento
	*/
	@Override
	public String toString() {
		return this.identificador;
	}
}
