package cotacaoEscolar.service.impl;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.listas.ListaEstabelecimento;
import cotacaoEscolar.repository.EstabelecimentoRepository;
import cotacaoEscolar.service.ServicoEstabelecimento;

@Service
public class ServicoEstabelecimentoLocal implements ServicoEstabelecimento {

   private final EstabelecimentoRepository repository;

   public ServicoEstabelecimentoLocal(final EstabelecimentoRepository repository) {
      this.repository = repository;
   }

   @Override
   public ListaEstabelecimento todos() {
      return this.repository.estabelecimentos();
   }

}
