package cotacaoEscolar.item.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cotacaoEscolar.item.model.DescricaoMaterialEscolar;
import cotacaoEscolar.item.model.ListaItem;

@Service
public interface ServicoItem {
   public ListaItem selecionePor(final int serie);

   public List<DescricaoMaterialEscolar> todasDescricoes();

}
