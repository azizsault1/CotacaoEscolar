package cotacaoEscolar.controller;

import java.util.Collection;

import cotacaoEscolar.model.DescricoesMaterialEscolar;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolar;
import cotacaoEscolar.repository.DescricaoMaterialEscolarRepository;

public class DescricoesMaterialEscolarController implements DescricoesMaterialEscolar {

   private final DescricaoMaterialEscolarRepository repository;

   public DescricoesMaterialEscolarController(final DescricaoMaterialEscolarRepository repository) {
      this.repository = repository;
   }

   @Override
   public Collection<DescricaoMaterialEscolar> todasDescricoes() {
      return this.repository.meDaTudo();
   }
}
