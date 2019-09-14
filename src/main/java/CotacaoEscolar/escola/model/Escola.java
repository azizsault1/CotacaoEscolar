package cotacaoEscolar.escola.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Escola {
   private final String nome;

   public Escola(final String nome) {
      this.nome = nome;
   }
   
   public String getNome() {
	return nome;
   }

   @Override
   public String toString() {
      return this.nome;
   }

}
