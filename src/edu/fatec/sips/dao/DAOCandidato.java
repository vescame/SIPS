package edu.fatec.sips.dao;

import java.io.IOException;

import edu.fatec.sips.controller.ArquivoCandidatoController;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Documento;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DAOCandidato implements DAOGenerico<Candidato, Documento> {
	ArquivoCandidatoController arquivoCandidato = new ArquivoCandidatoController();
	
	@Override
	public Candidato buscarPorId(int id) {
		return null;
	}
	
	@Override
	public Candidato buscarCampo(final Documento campo) {
		// buscar em todas as `colunas` por essa string
		// banco.buscar(campo.toString())
		return null;
	}

	@Override
	public ListaLigadaSimples<Candidato> buscarPorNome(final String nome) {
		return null;
	}

	@Override
	public ListaLigadaSimples<Candidato> listar() {
		try {
			return this.arquivoCandidato.listarCandidatos();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void salvar(final Candidato candidato){
		try {
			this.arquivoCandidato.gravarCandidato(candidato);
		} catch (IOException e) {
			System.out.println("falha ao salvar candidato");
		}
	}

	@Override
	public void atualizar(final Candidato candidato) {
		// podemos atualizar um candidato, seus recursos, aprovação
	}

	@Override
	public Candidato remover(Candidato elemento) {
		throw new NotImplementedException();
	}

	@Override
	public int ultimoId() {
		// busca o id usando a classe de controle de arquivos e retorna o id
		// para que possa ser usado, por exemplo, para adicionar novos registros sem perder o ID
		return 0;
	}
}
