package cotacaoEscolar.repository.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.repository.EscolaRepository;
import cotacaoEscolar.repository.pojos.EscolaPojo;

public class JsonEscola implements EscolaRepository {

   private final JsonRepository jsonDBTemplate;

   public JsonEscola(final JsonRepository repository) {
      this.jsonDBTemplate = repository;
   }

   @Override
   public Collection<Escola> escolas() {
      final List<EscolaPojo> pojos = this.jsonDBTemplate.pegaAPorraToda(EscolaPojo.class);
      return this.toModels(new ArrayList<>(pojos));
   }

   @Override
   public void salvaSaPorra(final Escola escola) {
      this.jsonDBTemplate.salvar(new EscolaPojo(escola));
   }

}
