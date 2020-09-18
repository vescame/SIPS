package edu.fatec.sips.tipos;

public enum Etapa {
	INSCRICAO("Incrição"),
	ANALISE_CURRICULO("Análise de Currículo"),
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
