package cotacaoEscolar.model.v1;

import cotacaoEscolar.app.exceptions.IllegalError;

public class Serie implements Comparable<Serie> {
   private final String serie;

   private Serie(final String serie) {
      this.serie = serie;
   }

   public static Serie create(final String serie) {
      if (serie == null) {
         throw new IllegalError("Opppss... dados inválidos.");
      }
      return new Serie(serie);
   }

   public String getSerie() {
      return this.serie;
   }

   @Override
   public int compareTo(final Serie o) {
      return this.serie.compareTo(o.getSerie());
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + ((this.serie == null) ? 0 : this.serie.hashCode());
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
      final Serie other = (Serie) obj;
      if (this.serie == null) {
         if (other.serie != null) {
            return false;
         }
      } else if (!this.serie.equals(other.serie)) {
         return false;
      }
      return true;
   }

}
