package cotacaoEscolar.model.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import cotacaoEscolar.model.Escola;

public class PrimeiraEscola implements Escola {

   @JsonCreator
   public static Escola create(@JsonProperty("nome") final String nome) {
      return new PrimeiraEscola(nome);
   }

   public static Escola create() {
      return new PrimeiraEscola();
   }

   private final String nome;

   public PrimeiraEscola(final String nome) {
      this.nome = nome;
   }

   public PrimeiraEscola() {
      this.nome = "";
   }

   @Override
   public String getNome() {
      return this.nome;
   }

   @Override
   public int compareTo(final Escola o) {
      return this.nome.compareTo(this.nome);
   }

}
