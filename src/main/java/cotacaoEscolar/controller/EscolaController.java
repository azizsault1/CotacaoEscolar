package cotacaoEscolar.controller;

import java.util.Optional;

import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.service.ServicoEscola;

public class EscolaController {

   private final ServicoEscola servicoEscola;

   public EscolaController(final ServicoEscola servicoEscola) {
      this.servicoEscola = servicoEscola;
   }

   private Escola getEscola(final String nomeEscola) {
      final Optional<Escola> escolaProcurada = this.servicoEscola.buscar(nomeEscola);
      if (escolaProcurada.isPresent()) {
         throw new IllegalError("Não encontrei a escola: " + nomeEscola);
      }
      return escolaProcurada.get();
   }
}
