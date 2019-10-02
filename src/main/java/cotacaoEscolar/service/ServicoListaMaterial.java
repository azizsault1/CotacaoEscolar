package cotacaoEscolar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.Item;
import cotacaoEscolar.model.v1.Serie;

@Service
public interface ServicoListaMaterial {

   ListaMaterial salvar(Escola escola, Serie serie);

   Collection<ListaMaterial> selecionePor(Escola escola);

   ListaMaterial selecionePor(Escola escolaEscolhida, Serie serieEscolhida);

   void remover(Escola escola, Serie serie, Item de);

   void adicionar(Escola escola, Serie serie, Item para);

   Collection<Serie> selecioneSeriesPor(Escola escolaEncontrada);

   ListaMaterial meDaUmMaterial();

}
