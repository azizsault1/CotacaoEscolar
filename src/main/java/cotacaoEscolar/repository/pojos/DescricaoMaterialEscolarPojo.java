package cotacaoEscolar.repository.pojos;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "descricoes", schemaVersion = "1.0")
public class DescricaoMaterialEscolarPojo implements ParserToModel<DescricaoMaterialEscolar> {

   @Id
   private String descricao;

   public DescricaoMaterialEscolarPojo() {
   }

   public DescricaoMaterialEscolarPojo(final String descricao) {
      this.descricao = descricao;
   }

   public String getDescricao() {
      return this.descricao;
   }

   public void setDescricao(final String descricao) {
      this.descricao = descricao;
   }

   @Override
   public DescricaoMaterialEscolar toModel() {
      return new DescricaoMaterialEscolar(this.descricao);
   }

}
