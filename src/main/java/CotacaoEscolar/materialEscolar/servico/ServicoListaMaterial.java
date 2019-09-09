package CotacaoEscolar.materialEscolar.servico;

import java.util.List;

import CotacaoEscolar.escola.model.Escola;
import CotacaoEscolar.item.model.ListaItem;
import CotacaoEscolar.materialEscolar.modelo.ListaMaterial;

public interface ServicoListaMaterial {

   List<ListaMaterial> selecionePor(Escola escola);

   ListaItem selecionePor(Escola escolaEscolhida, Integer serieEscolhida);

}
