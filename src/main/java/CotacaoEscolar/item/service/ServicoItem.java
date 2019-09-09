package CotacaoEscolar.item.service;

import java.util.List;

import CotacaoEscolar.item.model.DescricaoMaterialEscolar;
import CotacaoEscolar.item.model.ListaItem;

public interface ServicoItem {
   public ListaItem selecionePor(final int serie);

   public List<DescricaoMaterialEscolar> todasDescricoes();

}
