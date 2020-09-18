package edu.fatec.sips.tipos;

public enum Etapa {
	INSCRICAO("Incri��o"),
	ANALISE_CURRICULO("An�lise de Curr�culo"),
	ENTREVISTA("Entrevista"),
	RESULTADO_PRELIMINAR("Resultado Preliminar");
	
	private String descricao; 
	
	Etapa(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * @return Nome formatado da etapa
	 */
	@Override
	public String toString() {
		return this.descricao;
	}
}
