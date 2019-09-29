package cotacaoEscolar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import cotacaoEscolar.app.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.repository.EscolaRepository;

@Service
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
      final Escola aProcurar = new Escola(escola);
      final Collection<Escola> escolas = this.todas();
      return escolas.stream().filter(encontrada -> encontrada.equals(aProcurar)).findFirst()
            .orElseThrow(() -> new IllegalError("Nao achei a escola: " + escola + "."));
   }

   public void salvar(final Escola escola) {
      this.repository.salvaSaPorra(escola);
   }

}
