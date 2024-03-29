package cotacaoEscolar.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.repository.DescricaoMaterialEscolarRepository;

public class ServicoDescricaoMaterialEscolarLocal implements cotacaoEscolar.service.ServicoDescricaoMaterialEscolar {

   private final DescricaoMaterialEscolarRepository repository;

   @Autowired
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

   @Override
   public void salvar(final DescricaoMaterialEscolar descricaoMaterial) throws FoiNao {
      this.repository.salvaSaPorra(descricaoMaterial);
   }

}
