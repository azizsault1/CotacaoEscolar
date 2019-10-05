package cotacaoEscolar.controller.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.v1.EscolaReal;
import io.swagger.annotations.ApiModel;

@JsonRootName("serie")
@ApiModel(description = "Classe ou ano curricular de uma escola.")
public class Serie {

   private String escola;
   private String serie;

   @JsonCreator
   public Serie(@JsonProperty("escola") final String escola, @JsonProperty("serie") final String serie) {
      this.validate();
      this.escola = escola;
      this.serie = serie;
   }

   @JsonIgnore
   public String getEscola() {
      return this.escola;
   }

   public void setSerie(final String serie) {
      this.serie = serie;
   }

   public void setEscola(final String escola) {
      this.escola = escola;
   }

   public cotacaoEscolar.model.v1.Serie getSerie() {
      return cotacaoEscolar.model.v1.Serie.create(this.serie);
   }

   public void validate() {
      if (this.escola == null) {
         throw new IllegalError("Escola invalida");
      }

      final Escola model = EscolaReal.create(this.escola);

      if ((model == null) || !model.validate()) {
         throw new IllegalError("Escola invalida");
      }

      if ((this.serie == null) || (this.serie.trim().isEmpty())) {
         throw new IllegalError("Serie invalida");
      }
   }

   @JsonIgnore
   public Escola getEscolaModel() {
      return EscolaReal.create(this.escola);
   }
}