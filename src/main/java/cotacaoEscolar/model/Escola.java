package cotacaoEscolar.model;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.v1.helpers.Manjada;

public interface Escola extends Comparable<Escola>, Manjada<Escola> {

   String getNome();

   static Escola PrimeiraEscola() {
      return new PrimeiraEscola();
   }

   class PrimeiraEscola implements Escola {

      private final Manjada<Escola> naoSouManjada;

      public PrimeiraEscola() {
         this.naoSouManjada = new Manjada.ModelNovo<>();
      }

      @Override
      public String getNome() {
         return "";
      }

      @Override
      public int compareTo(final Escola o) {
         return -1;
      }

      @Override
      public boolean souNova() {
         return this.naoSouManjada.souNova();
      }

      @Override
      public Escola salvar() throws FoiNao {
         throw new UnsupportedOperationException("Opa, vc devereia ter chamado a Escola.create(String) pra poder salvar.");
      }

   }

}
