package edu.fatec.sips.dao;

import edu.fatec.sips.data_structure.ListaLigadaSimples;

public interface DAOGenerico<T, C> {
	public T buscarCampo(C campo);
	
	public ListaLigadaSimples<T> buscarPorNome(String nome);
	
	public ListaLigadaSimples<T> listar();
	
	public void salvar(T elemento);
	
	public void atualizar(T elemento);
	
	public T remover(T elemento);
}