package cotacaoEscolar.service.impl;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.listas.ListaEstabelecimento;
import cotacaoEscolar.repository.Repository;
import cotacaoEscolar.service.ServicoEstabelecimento;

@Service
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
