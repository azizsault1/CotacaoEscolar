package cotacaoEscolar.repository.pojos;

import java.util.ArrayList;
import java.util.List;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.listas.ListaMaterial;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "listaMateriais", schemaVersion = "1.0")
public class ListaMaterialPojo implements ParserToModel<ListaMaterial> {

   @Id
   private EscolaPojo escola;
   private String serie;
   private List<ItemPojo> itens;

   public ListaMaterialPojo() {
   }

   public ListaMaterialPojo(final ListaMaterial listaMaterial) {
      this.escola = new EscolaPojo(listaMaterial.getEscola());
      this.serie = listaMaterial.getSerie();
      this.itens = new ArrayList<>();
      listaMaterial.getItens().forEach(pojo -> this.itens.add(new ItemPojo(pojo)));
   }

   public EscolaPojo getEscola() {
      return this.escola;
   }

   public void setEscola(final EscolaPojo escola) {
      this.escola = escola;
   }

   public String getSerie() {
      return this.serie;
   }

   public void setSerie(final String serie) {
      this.serie = serie;
   }

   public List<ItemPojo> getItens() {
      return this.itens;
   }

   public void setItens(final List<ItemPojo> itens) {
      this.itens = itens;
   }

   @Override
   public ListaMaterial toModel() {
      final Escola escolaModel = this.escola.toModel();

      final List<Item> models = new ArrayList<>();
      this.itens.forEach(pojo -> models.add(pojo.toModel()));

      return new ListaMaterial(escolaModel, this.serie, models);
   }

}
