package edu.fatec.sips.dao;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Documento;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DAOCandidato implements DAOGenerico<Candidato, Documento> {

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
		return null;
	}

	@Override
	public void salvar(final Candidato candidato) {
		throw new NotImplementedException();
	}

	@Override
	public void atualizar(final Candidato candidato) {
		// podemos atualizar um candidato, seus recursos, aprovação
	}

	@Override
	public Candidato remover(Candidato elemento) {
		throw new NotImplementedException();
	}
}
