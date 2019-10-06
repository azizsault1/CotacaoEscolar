package cotacaoEscolar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolar;
import cotacaoEscolar.repository.DescricaoMaterialEscolarRepository;

public class ServicoDescricaoMaterialEscolarImpl implements cotacaoEscolar.service.ServicoDescricaoMaterialEscolar {

   private final DescricaoMaterialEscolarRepository repository;

   @Autowired
   public ServicoDescricaoMaterialEscolarImpl(final DescricaoMaterialEscolarRepository repository) {
      this.repository = repository;
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
