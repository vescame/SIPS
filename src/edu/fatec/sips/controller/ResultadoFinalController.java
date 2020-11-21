package edu.fatec.sips.controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.data_structure.sorting.MergeSortResultadoPreliminarPorNota;
import edu.fatec.sips.data_structure.sorting.QuickSortResultadoPreliminarEdital;
import edu.fatec.sips.file_controller.ArquivoResultadoFinalController;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Edital;
import edu.fatec.sips.model.ResultadoFinal;
import edu.fatec.sips.model.ResultadoPreliminar;

public class ResultadoFinalController {
	private final ArquivoResultadoFinalController bdResFinal;
	private final ResultadoPreliminarController ctrlResPrelim;
	private final EditalController ctrlEdital;
	private final CandidatoController ctrlCandidato;
	private final ListaLigadaSimples<ResultadoFinal> resultadosFinais;
	private final ListaLigadaSimples<ResultadoPreliminar> resultadosPreliminares;
	private final ListaLigadaSimples<Edital> editais;
	private ListaLigadaSimples<Candidato> candidatos;

	public ResultadoFinalController() {
		this.bdResFinal = new ArquivoResultadoFinalController();
		this.resultadosFinais = new ListaLigadaSimples<ResultadoFinal>();

		this.ctrlResPrelim = new ResultadoPreliminarController();
		this.resultadosPreliminares = this.ctrlResPrelim.listarResultadoPreliminar();

		this.ctrlEdital = new EditalController();
		this.editais = this.ctrlEdital.listarEditais();

		this.ctrlCandidato = new CandidatoController();
		this.candidatos = new ListaLigadaSimples<Candidato>();
	}

	public ResultadoFinal getPorCandidato(int id) {
		return null;
	}

	public ListaLigadaSimples<ResultadoFinal> listarResultados() {
		ListaLigadaSimples<ResultadoFinal> resultados = null;
		try {
			resultados = this.bdResFinal.listarResultado();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resultados;
	}

	public void iniciarResultado() {
		if (this.resultadosPreliminares.getTamanho() == 0) {
			JOptionPane.showMessageDialog(null, "Realize os resultados preliminares antes!");
			return;
		}

		final ListaLigadaSimples<ListaLigadaSimples<ResultadoPreliminar>> resultados = new ListaLigadaSimples<>();
		final int qtdEdital = editais.getTamanho();

		final ListaLigadaSimples<ResultadoPreliminar> ordenadoPorEdital = ordenarCandidatosPorEdital(
				this.resultadosPreliminares);

		for (int i = 0; i < qtdEdital; ++i) {
			ListaLigadaSimples<ResultadoPreliminar> r = dividirEditais(this.editais.espiar(i), ordenadoPorEdital);
			resultados.adicionar(r);
		}

		for (int i = 0; i < qtdEdital; ++i) {
			ListaLigadaSimples<ResultadoPreliminar> r = resultados.espiar(i);
			r = preencherVagasPorEdital(r);
			resultados.adicionar(r);
			final int qtdCandidatosPorEdital = r.getTamanho();
			for (int j = 0; j < qtdCandidatosPorEdital; ++j) {
				this.candidatos.adicionar(r.espiar(j).getCandidato());
			}
		}

		encerrarResultados();
	}

	private void encerrarResultados() {
		aprovarCandidatos();

		try {
			Candidato c = null;
			while ((c = this.candidatos.removerPrimeiro()) != null) {
				ResultadoFinal r = new ResultadoFinal(c);
				this.resultadosFinais.adicionar(r);
			}
			this.bdResFinal.salvarResultadosFinais(this.resultadosFinais);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ListaLigadaSimples<ResultadoPreliminar> dividirEditais(Edital edital,
			ListaLigadaSimples<ResultadoPreliminar> resPreliOrdenadosPorEdital) {
		ListaLigadaSimples<ResultadoPreliminar> resPorEdital = new ListaLigadaSimples<>();

		boolean editalEncontrado = false;

		int i = 0;
		while (!editalEncontrado) {
			final ResultadoPreliminar r = resPreliOrdenadosPorEdital.espiar(i);
			if (r.getCandidato().getEdital().getId() == edital.getId()) {
				editalEncontrado = true;
			}
			i++;
		}

		--i;
		ResultadoPreliminar r;
		while ((r = resPreliOrdenadosPorEdital.espiar(i)) != null
				&& r.getCandidato().getEdital().getId() == edital.getId()) {
			resPorEdital.adicionar(r);
			i++;
		}

		return resPorEdital;
	}

	private ListaLigadaSimples<ResultadoPreliminar> preencherVagasPorEdital(
			ListaLigadaSimples<ResultadoPreliminar> resPrelim) {
		resPrelim = ordenarPorNota(resPrelim);

		final ListaLigadaSimples<ResultadoPreliminar> tmp = new ListaLigadaSimples<>();

		final Edital edital = resPrelim.espiar(0).getCandidato().getEdital();

		final int vagas = edital.getAcoesAfirmativas() + edital.getAmplaConcorrencia() + edital.getDeficiente();

		final int tamanho = resPrelim.getTamanho();

		for (int i = 0; i < vagas; ++i) {
			if (i <= tamanho) {
				ResultadoPreliminar tmpResPrelim = resPrelim.espiar(i);
				if (candidatoMesmoCriterioEdital(tmpResPrelim.getCandidato())) {
					tmp.adicionar(tmpResPrelim);
				} else if (candidatoNaoDesistente(tmpResPrelim.getCandidato())) {
					tmp.adicionar(tmpResPrelim);
				}
			}
		}

		return tmp;
	}

	private ListaLigadaSimples<ResultadoPreliminar> ordenarPorNota(ListaLigadaSimples<ResultadoPreliminar> resultados) {
		final int tamanho = resultados.getTamanho();
		ListaLigadaSimples<ResultadoPreliminar> resultadosOrdenados = new ListaLigadaSimples<>();
		ResultadoPreliminar[] vetorResultados = new ResultadoPreliminar[tamanho];

		for (int i = 0; i < tamanho; ++i) {
			vetorResultados[i] = resultados.espiar(i);
		}

		MergeSortResultadoPreliminarPorNota ms = new MergeSortResultadoPreliminarPorNota();

		vetorResultados = ms.sort(vetorResultados);

		for (int i = 0; i < tamanho; ++i) {
			resultadosOrdenados.adicionar(vetorResultados[i]);
		}

		return resultadosOrdenados;
	}

	private ListaLigadaSimples<ResultadoPreliminar> ordenarCandidatosPorEdital(
			ListaLigadaSimples<ResultadoPreliminar> res) {
		final ListaLigadaSimples<ResultadoPreliminar> tmp = new ListaLigadaSimples<ResultadoPreliminar>();
		final QuickSortResultadoPreliminarEdital qs = new QuickSortResultadoPreliminarEdital();
		final int tamanho = res.getTamanho();

		final ResultadoPreliminar[] vetor = new ResultadoPreliminar[tamanho];

		for (int i = 0; i < tamanho; ++i) {
			vetor[i] = res.espiar(i);
		}

		qs.sort(vetor, 0, tamanho - 1);

		for (int i = 0; i < tamanho; ++i) {
			final ResultadoPreliminar tmpResPrelim = vetor[i];
			tmp.adicionar(tmpResPrelim);
		}

		return tmp;
	}

	private boolean candidatoMesmoCriterioEdital(Candidato candidato) {
		return candidato.getCriterio() == candidato.getEdital().getCriterio();
	}

	private boolean candidatoNaoDesistente(Candidato candidato) {
		return !candidato.isDesistente();
	}

	private void aprovarCandidatos() {
		final int tamanho = this.candidatos.getTamanho();

		for (int i = 0; i < tamanho; ++i) {
			final Candidato candidato = this.candidatos.espiar(i);
			candidato.setAprovado(true);
		}

		this.ctrlCandidato.atualizar(this.candidatos);
	}
}
