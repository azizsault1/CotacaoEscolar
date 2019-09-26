package cotacaoEscolar.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Item;

@Service
public interface ServicoItem {
   List<Item> selecionePor(final int serie);

   Collection<DescricaoMaterialEscolar> todasDescricoes();

   DescricaoMaterialEscolar selecionarPor(String materialEscolar);

}
