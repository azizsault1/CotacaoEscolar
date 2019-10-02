package cotacaoEscolar.model;

import cotacaoEscolar.service.ServicoEscola;

public interface Escola extends Comparable<Escola> {

   String getNome();

   boolean validate();

   public void save();

   static Escola meDaUmaEscola(final ServicoEscola servicoEscola) {
      return servicoEscola.meMaUmaEscola();
   }

   static Escola PrimeiraEscola() {
      return new PrimeiraEscola();
   }

   class PrimeiraEscola implements Escola {

      public PrimeiraEscola() {
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
      public void save() {
      }

      @Override
      public int compareTo(final Escola o) {
         return -1;
      }
   }
}