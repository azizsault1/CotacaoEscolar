package cotacaoEscolar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.listas.ListaItem;
import cotacaoEscolar.model.listas.ListaMaterial;

@Service
public interface ServicoListaMaterial {

   Collection<ListaMaterial> selecionePor(Escola escola);

   ListaItem selecionePor(Escola escolaEscolhida, Integer serieEscolhida);

   void remover(Escola escola, Integer serie, Item de);

   void adicionar(Escola escola, Integer serie, Item para);

   Collection<Integer> selecioneSeriesPor(Escola escolaEncontrada);

}
