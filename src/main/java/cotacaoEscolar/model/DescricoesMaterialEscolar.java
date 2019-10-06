package cotacaoEscolar.model;

import java.util.Collection;

import cotacaoEscolar.model.v1.DescricaoMaterialEscolar;
import cotacaoEscolar.repository.DescricaoMaterialEscolarRepository;

public interface DescricoesMaterialEscolar {

   Collection<DescricaoMaterialEscolar> todasDescricoes();

   public static DescricoesMaterialEscolar create(final DescricaoMaterialEscolarRepository repository) {
      return () -> {
         return repository.meDaTudo();
      };
   }

}
