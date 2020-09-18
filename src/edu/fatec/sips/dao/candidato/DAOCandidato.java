package edu.fatec.sips.dao.candidato;

import edu.fatec.sips.dao.DAOGenerico;
import edu.fatec.sips.data_structure.ListaLigada;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.base.DocumentoGenerico;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DAOCandidato implements DAOGenerico<Candidato, DocumentoGenerico> {

	@Override
	public Candidato buscarCampo(DocumentoGenerico campo) {
		// buscar em todas as `colunas` por essa string
		// banco.buscar(campo.toString())
		return null;
	}

	@Override
	public Candidato buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListaLigada<Candidato> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvar(Candidato elemento) {
		throw new NotImplementedException();
	}

	@Override
	public void atualizar(Candidato elemento) {
		// podemos atualizar um candidato, seus recursos, aprovação
	}

	@Override
	public Candidato remover(Candidato elemento) {
		throw new NotImplementedException();
	}

}
