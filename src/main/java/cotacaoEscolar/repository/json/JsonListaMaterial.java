package cotacaoEscolar.repository.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.repository.ListaMaterialRepository;
import cotacaoEscolar.repository.pojos.ListaMaterialPojo;

public class JsonListaMaterial implements ListaMaterialRepository {

   private final JsonRepository repository;

   public JsonListaMaterial(final JsonRepository repository) {
      this.repository = repository;
   }

   @Override
   public void salvaSaPorra(final ListaMaterial listaMaterial) {
      this.repository.salvar(new ListaMaterialPojo(listaMaterial));
   }

   @Override
   public Collection<ListaMaterial> materiais() {
      final List<ListaMaterialPojo> pojos = this.repository.pegaAPorraToda(ListaMaterialPojo.class);
      return this.toModels(new ArrayList<>(pojos));
   }

}
