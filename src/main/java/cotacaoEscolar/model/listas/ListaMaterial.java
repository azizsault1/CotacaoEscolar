package cotacaoEscolar.model.listas;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cotacaoEscolar.app.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;

@JsonSerialize
public class ListaMaterial implements Serializable {
   private static final long serialVersionUID = 1L;
   private final Escola escola;
   private final String serie;
   private final List<Item> itens;

   @JsonCreator
   public ListaMaterial(@JsonProperty("escola") final Escola escola, @JsonProperty("serie") final String serie, @JsonProperty("itens") final List<Item> itens) {

      if (escola == null) {
         throw new IllegalError("Opps... essa escola não existe.");
      }

      if ((serie == null) || serie.trim().isEmpty()) {
         throw new IllegalError("Opps... essa serie não existe.");
      }

      if (itens == null) {
         throw new IllegalError("Me passe uma lista vazia mais não me passe nulo, por favor!!");
      }

      this.escola = escola;
      this.serie = serie;
      this.itens = itens;
   }

   public Escola getEscola() {
      return this.escola;
   }

   public String getSerie() {
      return this.serie;
   }

   @JsonIgnore
   public boolean pertenceA(final Escola escola) {
      return this.escola.equals(escola);
   }

   public List<Item> getItens() {
      return this.itens;
   }

   @JsonIgnore
   public boolean pertenceASerie(final String serie) {
      return this.serie.equals(serie);
   }

   public void addItem(final Item item) {
      this.itens.add(item);
   }

   public static class Factory {
      private Factory() {
      }

      public static ListaMaterial criarListaVazia() {
         return new ListaMaterial(new Escola("EscolaInexistente"), "1", Collections.emptyList());
      }
   }

}
