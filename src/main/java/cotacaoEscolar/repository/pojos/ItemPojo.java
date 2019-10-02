package cotacaoEscolar.repository.pojos;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Item;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "itens", schemaVersion = "1.0")
public class ItemPojo implements ParserToModel<Item> {

   @Id
   private DescricaoMaterialEscolarPojo materialEscolar;
   private int quantidade;

   public ItemPojo() {
   }

   public ItemPojo(final Item item) {
      this.materialEscolar = new DescricaoMaterialEscolarPojo(item.getMaterialEscolar().getDescricao());
      this.quantidade = item.getQuantidade();
   }

   public DescricaoMaterialEscolarPojo getMaterialEscolar() {
      return this.materialEscolar;
   }

   public void setMaterialEscolar(final DescricaoMaterialEscolarPojo materialEscolar) {
      this.materialEscolar = materialEscolar;
   }

   public int getQuantidade() {
      return this.quantidade;
   }

   public void setQuantidade(final int quantidade) {
      this.quantidade = quantidade;
   }

   @Override
   public Item toModel() {
      final DescricaoMaterialEscolar descricaoModel = this.materialEscolar.toModel();

      return Item.create(descricaoModel.getDescricao(), this.quantidade);
   }

}
