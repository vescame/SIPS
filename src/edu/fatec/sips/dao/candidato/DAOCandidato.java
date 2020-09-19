package edu.fatec.sips.dao.candidato;

import edu.fatec.sips.dao.DAOGenerico;
import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.base.DocumentoGenerico;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DAOCandidato implements DAOGenerico<Candidato, DocumentoGenerico> {

	@Override
	public Candidato buscarCampo(final DocumentoGenerico campo) {
		// buscar em todas as `colunas` por essa string
		// banco.buscar(campo.toString())
		return null;
	}

	@Override
	public ListaLigadaSimples<Candidato> buscarPorNome(final String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaLigadaSimples<Candidato> listar() {
		// TODO Auto-generated method stub
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
