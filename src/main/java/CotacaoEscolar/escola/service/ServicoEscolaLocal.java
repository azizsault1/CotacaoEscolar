package CotacaoEscolar.escola.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CotacaoEscolar.escola.model.Escola;

public class ServicoEscolaLocal implements ServicoEscola {

   private static List<Escola> escolas = new ArrayList<>();

   public ServicoEscolaLocal() {
      final Escola escola1 = new Escola("Escola1");
      final Escola escola2 = new Escola("Escola2");
      final Escola escola3 = new Escola("Escola3");

      escolas = Arrays.asList(escola1, escola2, escola3);
   }

   @Override
   public final List<Escola> todas() {
      return escolas;
   }
}
