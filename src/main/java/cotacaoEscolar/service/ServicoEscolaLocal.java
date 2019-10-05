package cotacaoEscolar.service;

import java.util.Collection;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.Escola;
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
   public Escola buscar(final String escola) {
      final Escola aProcurar = Escola.create(escola);
      final Collection<Escola> escolas = this.todas();
      return escolas.stream().filter(encontrada -> encontrada.equals(aProcurar)).findFirst()
            .orElseThrow(() -> new IllegalError("Nao achei a escola: " + escola + "."));
   }

   @Override
   public void salvar(final Escola escola) throws FoiNao {
      this.repository.salvaSaPorra(escola);
   }

}
