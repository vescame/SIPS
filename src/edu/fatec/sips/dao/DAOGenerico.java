package edu.fatec.sips.dao;

import edu.fatec.sips.data_structure.ListaLigada;

public interface DAOGenerico<T, C> {
	public T buscarCampo(C campo);
	
	public T buscarPorNome(String nome);
	
	public ListaLigada<T> listar();
	
	public void salvar(T elemento);
	
	public void atualizar(T elemento);
	
	public T remover(T elemento);
}
