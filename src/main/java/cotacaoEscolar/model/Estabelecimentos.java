package cotacaoEscolar.model;

import cotacaoEscolar.model.v1.ListaEstabelecimento;
import cotacaoEscolar.model.v1.ResultadoCotacao;
import cotacaoEscolar.repository.EstabelecimentoRepository;

public interface Estabelecimentos {

   public ResultadoCotacao cotar(final ListaMaterial lista);

   public static Estabelecimentos create(final EstabelecimentoRepository repository) {
      return lista -> {
         final ListaEstabelecimento estabelecimentos = repository.estabelecimentos();
         return estabelecimentos.cotar(lista);
      };
   }
}
