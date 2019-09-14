package cotacaoEscolar.materialEscolar.servico;

import java.util.List;

import cotacaoEscolar.escola.model.Escola;
import cotacaoEscolar.item.model.Item;
import cotacaoEscolar.item.model.ListaItem;
import cotacaoEscolar.materialEscolar.modelo.ListaMaterial;

public interface ServicoListaMaterial {

   List<ListaMaterial> selecionePor(Escola escola);

   ListaItem selecionePor(Escola escolaEscolhida, Integer serieEscolhida);

   void remover(Escola escola, Integer serie, Item de);

   void adicionar(Escola escola, Integer serie, Item para);

}
