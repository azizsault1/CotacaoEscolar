package cotacaoEscolar.service;

import java.util.Collection;
import java.util.List;

import cotacaoEscolar.model.Item;
import org.springframework.stereotype.Service;

import cotacaoEscolar.model.DescricaoMaterialEscolar;

@Service
public interface ServicoItem {
   List<Item> selecionePor(final int serie);

   Collection<DescricaoMaterialEscolar> todasDescricoes();

}
