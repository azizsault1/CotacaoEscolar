package cotacaoEscolar.service.impl;

import java.util.Collection;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.repository.DescricaoMaterialEscolarRepository;

public class ServicoDescricaoMaterialEscolarLocal implements cotacaoEscolar.service.ServicoDescricaoMaterialEscolar {

   private final DescricaoMaterialEscolarRepository repository;

   public ServicoDescricaoMaterialEscolarLocal(final DescricaoMaterialEscolarRepository repository) {
      this.repository = repository;
   }

   @Override
   public Collection<DescricaoMaterialEscolar> todasDescricoes() {
      return this.repository.meDaTudo();
   }

   @Override
   public DescricaoMaterialEscolar selecionarPor(final String materialEscolar) {
      return this.repository.selecionarPor(DescricaoMaterialEscolar.create(materialEscolar));
   }

}
