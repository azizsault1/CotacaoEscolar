package cotacaoEscolar.service.impl;

import cotacaoEscolar.model.listas.ListaEstabelecimento;
import cotacaoEscolar.repository.Repository;
import cotacaoEscolar.service.ServicoEstabelecimento;

public class ServicoEstabelecimentoLocal implements ServicoEstabelecimento {

   private final Repository repository;

   public ServicoEstabelecimentoLocal(final Repository repository) {
      this.repository = repository;
   }

   @Override
   public ListaEstabelecimento todos() {
      return this.repository.estabelecimentos();
   }

}
