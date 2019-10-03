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
   public Escola toModel() {
      return EscolaReal.create(this.nome);
   }

}