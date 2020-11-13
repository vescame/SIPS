package edu.fatec.sips.enums;

public enum Etapa {
	INSCRICAO("Incrição"),
	ANALISE_CURRICULO("Análise de Currículo"),
	ENTREVISTA("Entrevista"),
	RESULTADO_PRELIMINAR("Resultado Preliminar"),
	RESULTADO_FINAL("Resultado Final");
	
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
