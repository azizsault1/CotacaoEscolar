package cotacaoEscolar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.ListaItem;
import cotacaoEscolar.model.ListaMaterial;

@Service
public interface ServicoListaMaterial {

   List<ListaMaterial> selecionePor(Escola escola);

   ListaItem selecionePor(Escola escolaEscolhida, Integer serieEscolhida);

   void remover(Escola escola, Integer serie, Item de);

   void adicionar(Escola escola, Integer serie, Item para);

   ServicoListaMaterial adicionar(Escola escola, Integer valueOf, ListaItem selecionePor);

}
