package cotacaoEscolar.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cotacaoEscolar.model.listas.ListaEstabelecimento;
import cotacaoEscolar.repository.EstabelecimentoRepository;
import cotacaoEscolar.service.ServicoEstabelecimento;

public class ServicoEstabelecimentoLocal implements ServicoEstabelecimento {

   private final EstabelecimentoRepository repository;

   @Autowired
   public ServicoEstabelecimentoLocal(final EstabelecimentoRepository repository) {
      this.repository = repository;
   }

   @Override
   public ListaEstabelecimento todos() {
      return this.repository.estabelecimentos();
   }

}
