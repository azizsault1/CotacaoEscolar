package cotacaoEscolar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.listas.ListaItem;

@Service
public interface ServicoItem {
   public ListaItem selecionePor(final int serie);

   public Collection<DescricaoMaterialEscolar> todasDescricoes();

}
