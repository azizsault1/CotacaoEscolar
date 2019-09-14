package cotacaoEscolar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.ListaItem;

@Service
public interface ServicoItem {
   public ListaItem selecionePor(final int serie);

   public List<DescricaoMaterialEscolar> todasDescricoes();

}
