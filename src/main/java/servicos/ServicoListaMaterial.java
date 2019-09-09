package servicos;

import java.util.List;

import CotacaoEscolar.ListaItem;
import CotacaoEscolar.ListaMaterial;
import CotacaoEscolar.escola.model.Escola;

public interface ServicoListaMaterial {

   List<ListaMaterial> selecionePor(Escola escola);

   ListaItem selecionePor(Escola escolaEscolhida, Integer serieEscolhida);

}
