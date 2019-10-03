package cotacaoEscolar.model;

import java.util.Optional;

import cotacaoEscolar.model.v1.Manjada;
import cotacaoEscolar.service.ServicoEscola;

public interface Escola extends Comparable<Escola>, Manjada<Escola> {

   String getNome();

   boolean validate();

   static Escola PrimeiraEscola() {
      return new PrimeiraEscola();
   }

   class PrimeiraEscola implements Escola, ServicoEscola {

      private final Manjada<Escola> naoSouManjada;

      public PrimeiraEscola() {
         this.naoSouManjada = new Manjada.ModelNovo<>();
      }

      @Override
      public String getNome() {
         return "";
      }

      @Override
      public boolean validate() {
         return false;
      }

      @Override
      public int compareTo(final Escola o) {
         return -1;
      }

      @Override
      public Optional<Escola> buscar(final String escola) {
         return Optional.empty();
      }

      @Override
      public void salvar(final Escola escola) {
         this.naoSouManjada.salvar(escola);
      }

      @Override
      public boolean souNova() {
         return this.naoSouManjada.souNova();
      }

   }

}