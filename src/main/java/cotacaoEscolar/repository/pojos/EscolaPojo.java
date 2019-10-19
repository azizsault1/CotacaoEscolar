package cotacaoEscolar.repository.pojos;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.v1.EscolaReal;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "escolas", schemaVersion = "1.0")
public class EscolaPojo implements ParserToModel<Escola> {

   @Id
   private String nome;

   public EscolaPojo(final Escola escola) {
      this.nome = escola.getNome();
   }

   public EscolaPojo() {
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(final String nome) {
      this.nome = nome;
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
      final EscolaPojo other = (EscolaPojo) obj;
      if (this.nome == null) {
         if (other.nome != null) {
            return false;
         }
      } else if (!this.nome.equals(other.nome)) {
         return false;
      }
      return true;
   }

   @Override
   public Escola toModel() {
      return EscolaReal.create(this.nome);
   }
}