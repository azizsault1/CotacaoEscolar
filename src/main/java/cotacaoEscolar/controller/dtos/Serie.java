package cotacaoEscolar.controller.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.v1.helpers.EscolaValidator;
import cotacaoEscolar.model.v1.helpers.Manjada;
import io.swagger.annotations.ApiModel;

@JsonRootName("serie")
@ApiModel(description = "Classe ou ano curricular de uma escola.")
public class Serie {

   private String escola;
   private String serie;

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

   private void validate() {
      if (this.escola == null) {
         throw new IllegalError("Escola invalida");
      }

      EscolaValidator.create(this.escola).validate();

      if ((this.serie == null) || (this.serie.trim().isEmpty())) {
         throw new IllegalError("Serie invalida");
      }
   }

   @JsonIgnore
   public Escola getEscolaModel() {
      return new EscolaImpl(this.escola);
   }

   class EscolaImpl implements Escola {

      private final Manjada<Escola> naoSouManjada;
      private final String nome;

      public EscolaImpl(final String nome) {
         this.naoSouManjada = new Manjada.ModelNovo<>();
         this.nome = nome;
      }

      @Override
      public int compareTo(final Escola o) {
         return -1;
      }

      @Override
      public Escola salvar() throws FoiNao {
         return this.naoSouManjada.salvar();
      }

      @Override
      public boolean souNova() {
         return this.naoSouManjada.souNova();
      }

      @Override
      public String getNome() {
         return this.nome;
      }

   }
}