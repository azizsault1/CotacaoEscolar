package cotacaoEscolar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.repository.Repository;

@Service
public class ServicoEscolaLocal implements ServicoEscola {

   private final Repository repository;

   public ServicoEscolaLocal(final Repository repository) {
      this.repository = repository;
   }

   @Override
   public final Collection<Escola> todas() {
      return this.repository.escolas();
   }

   @Override
   public Escola buscar(final String escola) {
      final Escola aProcurar = new Escola(escola);
      final Collection<Escola> escolas = this.todas();
      return escolas.stream().filter(encontrada -> encontrada.equals(aProcurar)).findFirst().orElseThrow(IllegalArgumentException::new);
   }
}
