package cotacaoEscolar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.ItemImpl;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.model.v1.helpers.ListaMaterialAutoSave;

@Service
public interface ServicoListaMaterial {

   Collection<ListaMaterial> selecionePor(Escola escola);

   ListaMaterialAutoSave selecionePor(Escola escolaEscolhida, Serie serieEscolhida) throws FoiNao;

   void remover(Escola escola, Serie serie, ItemImpl de) throws FoiNao;

   void adicionar(Escola escola, Serie serie, ItemImpl para) throws FoiNao;

   Collection<Serie> selecioneSeriesPor(Escola escolaEncontrada);

}
