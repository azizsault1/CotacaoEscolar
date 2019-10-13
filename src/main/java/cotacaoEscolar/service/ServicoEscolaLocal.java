package cotacaoEscolar.service;

import java.util.Collection;
import java.util.Optional;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.v1.EscolaReal;
import cotacaoEscolar.repository.EscolaRepository;

public class ServicoEscolaLocal implements ServicoEscola {

   private final EscolaRepository repository;

   public ServicoEscolaLocal(final EscolaRepository repository) {
      this.repository = repository;
   }

   @Override
   public final Collection<Escola> todas() {
      return this.repository.escolas();
   }

   @Override
   public Optional<Escola> buscar(final String escola) {
      final Escola aProcurar = EscolaReal.create(this, escola);
      final Collection<Escola> escolas = this.todas();
      //@formatter:off
      return escolas.stream()
            .filter(encontrada -> encontrada.equals(aProcurar))
            .findFirst();

      //@formatter:off
   }

   @Override
   public Escola salvar(final Escola escola) throws FoiNao {
      return this.repository.salvaSaPorra(escola);
   }

}
