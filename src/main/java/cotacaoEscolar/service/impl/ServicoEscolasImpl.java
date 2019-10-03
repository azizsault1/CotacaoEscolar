package cotacaoEscolar.service.impl;

import java.util.Collection;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.repository.EscolaRepository;
import cotacaoEscolar.service.ServicoEscolas;

public class ServicoEscolasImpl implements ServicoEscolas {

   private final EscolaRepository repository;

   public ServicoEscolasImpl(final EscolaRepository repository) {
      this.repository = repository;
   }

   @Override
   public final Collection<Escola> todas() {
      return this.repository.escolas();
   }

}
