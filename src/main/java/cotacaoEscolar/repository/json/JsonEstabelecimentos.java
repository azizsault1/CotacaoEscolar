package cotacaoEscolar.repository.json;

import java.util.ArrayList;
import java.util.List;

import cotacaoEscolar.model.v1.Estabelecimento;
import cotacaoEscolar.model.v1.ListaEstabelecimento;
import cotacaoEscolar.repository.EstabelecimentoRepository;
import cotacaoEscolar.repository.pojos.EstabelecimentoPojo;

public class JsonEstabelecimentos implements EstabelecimentoRepository {

   private final JsonRepository repository;

   public JsonEstabelecimentos(final JsonRepository repository) {
      this.repository = repository;
   }

   @Override
   public ListaEstabelecimento estabelecimentos() {
      final List<EstabelecimentoPojo> pojos = this.repository.pegaAPorraToda(EstabelecimentoPojo.class);
      final List<Estabelecimento> estabelecimentos = this.toModels(new ArrayList<>(pojos));
      return new ListaEstabelecimento(estabelecimentos);
   }

   @Override
   public void salvaSaPorra(final Estabelecimento estabelecimento) {
      this.repository.salvar(new EstabelecimentoPojo(estabelecimento));
   }

}
