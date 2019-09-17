package cotacaoEscolar.model.listas;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cotacaoEscolar.model.Escola;

/**
 * Escreva a descrição da classe ListaMaterial aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
@JsonSerialize
public class ListaMaterial implements Serializable {
   private static final long serialVersionUID = 1L;
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
   private final Escola escola;
   private final Integer serie;
   private final ListaItem itens;

   @JsonCreator
   public ListaMaterial(@JsonProperty("escola") final Escola escola, @JsonProperty("serie") final Integer serie, @JsonProperty("itens") final ListaItem itens) {
      this.escola = escola;
      this.serie = serie;
      this.itens = itens;
   }

   public Escola getEscola() {
      return this.escola;
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
