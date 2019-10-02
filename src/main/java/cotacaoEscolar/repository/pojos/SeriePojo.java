package cotacaoEscolar.repository.pojos;

import cotacaoEscolar.model.v1.Serie;

public class SeriePojo implements ParserToModel<Serie> {

   private String serie;

   public SeriePojo(final Serie serie) {
      this.serie = serie.getSerie();
   }

   public String getSerie() {
      return this.serie;
   }

   public void setSerie(final String serie) {
      this.serie = serie;
   }

   @Override
   public Serie toModel() {
      return Serie.create(this.serie);
   }

}
