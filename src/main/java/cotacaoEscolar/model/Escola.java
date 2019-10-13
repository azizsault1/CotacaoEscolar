package cotacaoEscolar.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Instituição pública ou privada destinado ao ensino coletivo.")
public interface Escola extends Comparable<Escola> {

   String getNome();

   @JsonCreator
   public static Escola create(@JsonProperty("nome") final String nome) {
      return new PrimeiraEscola(nome);
   }

   public static Escola create() {
      return new PrimeiraEscola();
   }

   final class PrimeiraEscola implements Escola {

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

}
