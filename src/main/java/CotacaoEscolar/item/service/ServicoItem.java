package CotacaoEscolar.item.service;

import java.util.List;

import CotacaoEscolar.ListaItem;
import CotacaoEscolar.item.model.DescricaoMaterialEscolar;

public interface ServicoItem {
   public ListaItem selecionePor(final int serie);

   public List<DescricaoMaterialEscolar> todasDescricoes();

}
