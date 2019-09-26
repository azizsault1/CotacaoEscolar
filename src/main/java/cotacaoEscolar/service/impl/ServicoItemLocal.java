package cotacaoEscolar.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.repository.Repository;
import cotacaoEscolar.service.ServicoItem;

@Service
public class ServicoItemLocal implements ServicoItem {

   private final Repository repository;

   public ServicoItemLocal(final Repository repository) {
      this.repository = repository;

   }

   @Override
   public List<Item> selecionePor(final int serie) {
      return this.repository.selecionePor(serie);
   }

   @Override
   public Collection<DescricaoMaterialEscolar> todasDescricoes() {
      return this.repository.todasDescricoes();
   }

   @Override
   public DescricaoMaterialEscolar selecionarPor(final String materialEscolar) {
      return this.repository.selecionarPor(DescricaoMaterialEscolar.create(materialEscolar));
   }

}
