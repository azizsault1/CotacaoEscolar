package cotacaoEscolar.service;

import java.util.Collection;
import java.util.Optional;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.v1.EscolaReal;
import cotacaoEscolar.repository.EscolaRepository;

public class ServicoEscolaLocal implements ServicoEscola {

   private final EscolaRepository repository;

   public ServicoEscolaLocal(final EscolaRepository repository) {
      this.repository = repository;
   }

   @Override
   public Optional<Escola> buscar(final String escola) {
      final Escola aProcurar = EscolaReal.create(escola);
      final Collection<Escola> escolas = this.repository.escolas();
      return escolas.stream().filter(encontrada -> encontrada.equals(aProcurar)).findFirst();
   }

   @Override
   public void salvar(final Escola escola) {
      this.repository.salvaSaPorra(escola);
   }

}
