package cotacaoEscolar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolarImpl;
import cotacaoEscolar.repository.DescricaoMaterialEscolarRepository;

public class ServicoDescricaoMaterialEscolarImpl implements cotacaoEscolar.service.ServicoDescricaoMaterialEscolar {

   private final DescricaoMaterialEscolarRepository repository;

   @Autowired
   public ServicoDescricaoMaterialEscolarImpl(final DescricaoMaterialEscolarRepository repository) {
      this.repository = repository;
   }

   @Override
   public DescricaoMaterialEscolar selecionarPor(final String materialEscolar) {
      return this.repository.selecionarPor(DescricaoMaterialEscolarImpl.create(materialEscolar));
   }

   @Override
   public void salvar(final DescricaoMaterialEscolarImpl descricaoMaterial) throws FoiNao {
      this.repository.salvaSaPorra(descricaoMaterial);
   }

}
