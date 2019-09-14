package cotacaoEscolar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Escreva a descrição da classe ListaMaterial aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
@JsonSerialize
public class ListaMaterial {
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
   private final Escola escola;
   private final Integer serie;
   private final ListaItem itens;

   /**
    * COnstrutor para objetos da classe ListaMaterial
    */
   public ListaMaterial(final Escola escola, final Integer serie, final ListaItem itens) {
      this.escola = escola;
      this.serie = serie;
      this.itens = itens;
   }

   public int getSerie() {
      return this.serie;
   }

   @JsonIgnore
   public boolean pertenceA(final Escola escola) {
      return this.escola.equals(escola);
   }

   public ListaItem getItens() {
      return this.itens;
   }

   @JsonIgnore
   public boolean pertenceA(final Integer serie) {
      return this.serie.equals(serie);
   }

}
