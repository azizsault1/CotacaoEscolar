package cotacaoEscolar.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Escreva a descrição da classe Escola aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
@JsonSerialize
public class Escola {
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
   private final String nome;

   /**
    * COnstrutor para objetos da classe Escola
    */
   public Escola(final String nome) {
      this.nome = nome;
   }

   public String getNome() {
      return this.nome;
   }

   @Override
   public String toString() {
      return this.nome;
   }

}
