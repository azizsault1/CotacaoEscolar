package cotacaoEscolar.controller.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import cotacaoEscolar.app.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.v1.EscolaReal;
import cotacaoEscolar.model.v1.Serie;

@JsonRootName("serie")
public class SerieDto {

   public String escola;
   public String serie;

   @JsonCreator
   public SerieDto(@JsonProperty("escola") final String escola, @JsonProperty("serie") final String serie) {
      this.escola = escola;
      this.serie = serie;
   }

   public String getEscola() {
      return this.escola;
   }

   @JsonIgnore
   public Escola getEscolaModel() {
      return EscolaReal.create(this.escola);
   }

   public Serie getSerie() {
      return Serie.create(this.serie);
   }

   public void validate() {
      if (this.escola == null) {
         throw new IllegalError("Escola invalida");
      }

      final Escola model = Escola.create(this.escola);

      if ((model == null) || !model.validate()) {
         throw new IllegalError("Escola invalida");
      }

      if ((this.serie == null) || (this.serie.trim().isEmpty())) {
         throw new IllegalError("Serie invalida");
      }
   }
}