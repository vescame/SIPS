package edu.fatec.sips.controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import edu.fatec.sips.data_structure.ListaLigadaSimples;
import edu.fatec.sips.data_structure.No;
import edu.fatec.sips.data_structure.search.BuscaBinariaCandidato;
import edu.fatec.sips.data_structure.search.BuscaBinariaResultadoFinalCandidato;
import edu.fatec.sips.data_structure.sorting.MergeSortCandidatos;
import edu.fatec.sips.data_structure.sorting.MergeSortResultadoPreliminarPorNota;
import edu.fatec.sips.data_structure.sorting.QuickSortResultadoPreliminarEdital;
import edu.fatec.sips.data_structure.sorting.ShellSortResultadoFinalPorCurso;
import edu.fatec.sips.file_controller.ArquivoResultadoFinalController;
import edu.fatec.sips.model.Candidato;
import edu.fatec.sips.model.Edital;
import edu.fatec.sips.model.ResultadoFinal;
import edu.fatec.sips.model.ResultadoPreliminar;

public class ResultadoFinalController {
	private final ArquivoResultadoFinalController bdResFinal;
	private final EditalController ctrlEdital;
	private final CandidatoController ctrlCandidato;
	private final ListaLigadaSimples<ResultadoFinal> resultadosFinais;
	private final ListaLigadaSimples<ResultadoPreliminar> resultadosPreliminares;
	private final ListaLigadaSimples<Candidato> candidatos;

	public ResultadoFinalController() {
		this.bdResFinal = new ArquivoResultadoFinalController();
		this.resultadosFinais = new ListaLigadaSimples<ResultadoFinal>();

		ResultadoPreliminarController ctrlResPrelim = new ResultadoPreliminarController();
		this.resultadosPreliminares = ctrlResPrelim.listarResultadoPreliminar();

		this.ctrlEdital = new EditalController();

		this.ctrlCandidato = new CandidatoController();
		this.candidatos = new ListaLigadaSimples<Candidato>();
	}

	public ResultadoFinal getPorCandidato(int id) {
		ListaLigadaSimples<ResultadoFinal> res;
		No<ResultadoFinal> encontrado = null;
		try {
			res = this.bdResFinal.listarResultado();
			BuscaBinariaResultadoFinalCandidato bbResCandidato = new BuscaBinariaResultadoFinalCandidato();
			encontrado = bbResCandidato.buscaBinaria(res.primeiro, id);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return encontrado != null ? encontrado.getElemento() : null;
	}

	public ListaLigadaSimples<ResultadoFinal> listarResultados() {
		ListaLigadaSimples<ResultadoFinal> resultados = null;
		try {
			resultados = this.bdResFinal.listarResultado();
			final int tamanho = resultados.getTamanho();
			ResultadoFinal[] vetor = new ResultadoFinal[tamanho];

			for (int i = 0; i < tamanho; ++i) {
				vetor[i] = resultados.removerPrimeiro();
			}

			ShellSortResultadoFinalPorCurso shellSortCurso = new ShellSortResultadoFinalPorCurso();

			shellSortCurso.sort(vetor);

			for (int i = 0; i < tamanho; ++i) {
			    resultados.adicionar(vetor[i]);
			}

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

		final ListaLigadaSimples<ResultadoPreliminar> ordenadoPorEdital = ordenarCandidatosPorEdital(this.resultadosPreliminares);

		final ListaLigadaSimples<Edital> editais = this.ctrlEdital.listarEditais();
		int qtdEdital = editais.getTamanho();

		for (int i = 0; i < qtdEdital; ++i) {
			ListaLigadaSimples<ResultadoPreliminar> r = dividirEditais(editais.espiar(i), ordenadoPorEdital);
			resultados.adicionar(r);
		}

		for (int i = 0; i < qtdEdital; ++i) {
			ListaLigadaSimples<ResultadoPreliminar> r = resultados.espiar(i);
			r = preencherVagasPorEdital(r);
			resultados.adicionar(r);
			final int qtdCandidatosPorEdital = r.getTamanho();
			for (int j = 0; j < qtdCandidatosPorEdital; ++j) {
				Candidato c = r.espiar(j).getCandidato();
				this.candidatos.adicionar(c);
			}
		}

		encerrarResultados();
	}

	private void encerrarResultados() {
		aprovarCandidatos();

		try {
			Candidato c = null;

			final int tamanho = this.candidatos.getTamanho();

			for (int i = 0; i < tamanho; ++i) {
			    c = this.candidatos.espiar(i);
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
		ResultadoPreliminar r;

		do {
			r = resPreliOrdenadosPorEdital.espiar(i);
			if (r != null && r.getCandidato().getEdital().getId() == edital.getId()) {
				editalEncontrado = true;
			}
			i++;
		} while (!editalEncontrado);


		if (!resPreliOrdenadosPorEdital.estaVazia()) {
			--i;
			while ((r = resPreliOrdenadosPorEdital.espiar(i)) != null
					&& r.getCandidato().getEdital().getId() == edital.getId()) {
				if (r.getCandidato().getEdital() != null) {
					resPorEdital.adicionar(r);
					i++;
				}
			}
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

		if (tamanho != 0) {
			final ResultadoPreliminar[] vetor = new ResultadoPreliminar[tamanho];

			for (int i = 0; i < tamanho; ++i) {
				vetor[i] = res.espiar(i);
			}

			qs.sort(vetor, 0, tamanho - 1);

			for (int i = 0; i < tamanho; ++i) {
				final ResultadoPreliminar tmpResPrelim = vetor[i];
				if (tmpResPrelim.getCandidato().getEdital() != null) {
					tmp.adicionar(tmpResPrelim);
				}
			}
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
