package cotacaoEscolar.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

   @JsonCreator
   public Escola(@JsonProperty("nome") final String nome) {
      this.nome = nome;
   }

   public String getNome() {
      return this.nome;
   }

   @Override
   public String toString() {
      return this.nome;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + ((this.nome == null) ? 0 : this.nome.hashCode());
      return result;
   }

   @Override
   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (this.getClass() != obj.getClass()) {
         return false;
      }
      final Escola other = (Escola) obj;
      if (this.nome == null) {
         if (other.nome != null) {
            return false;
         }
      } else if (!this.nome.equals(other.nome)) {
         return false;
      }
      return true;
   }

}
