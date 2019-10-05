package cotacaoEscolar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.Item;
import cotacaoEscolar.model.v1.Serie;

@Service
public interface ServicoListaMaterial {

   ListaMaterial salvar(Escola escola, Serie serie) throws FoiNao;

   Collection<ListaMaterial> selecionePor(Escola escola);

   ListaMaterial selecionePor(Escola escolaEscolhida, Serie serieEscolhida) throws FoiNao;

   void remover(Escola escola, Serie serie, Item de) throws FoiNao;

   void adicionar(Escola escola, Serie serie, Item para) throws FoiNao;

   Collection<Serie> selecioneSeriesPor(Escola escolaEncontrada);

   ListaMaterial meDaUmMaterial();

}
