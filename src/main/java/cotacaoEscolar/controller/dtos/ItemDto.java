package cotacaoEscolar.controller.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("item")
public class ItemDto {

   public String escola;
   public Integer serie;
   public String materialEscolar;
   public Integer quantidade;

   @JsonCreator
   public ItemDto(@JsonProperty("item") final String escola, @JsonProperty("serie") final Integer serie,
         @JsonProperty("materialEscolar") final String materialEscolar, @JsonProperty("quantidade") final Integer quantidade) {
      this.escola = escola;
      this.serie = serie;
      this.materialEscolar = materialEscolar;
      this.quantidade = quantidade;
   }

   public String getEscola() {
      return this.escola;
   }

   public Integer getSerie() {
      return this.serie;
   }

   public String getMaterialEscolar() {
      return this.materialEscolar;
   }

   public Integer quantidade() {
      return this.quantidade;
   }

}