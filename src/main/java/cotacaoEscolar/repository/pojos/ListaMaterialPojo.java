package cotacaoEscolar.repository.pojos;

import java.util.ArrayList;
import java.util.List;

import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.EscolaReal;
import cotacaoEscolar.model.v1.Item;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.repository.Repository;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "listaMateriais", schemaVersion = "1.0")
public class ListaMaterialPojo implements ParserToModel<ListaMaterial> {

   @Id
   private EscolaPojo escola;
   private SeriePojo serie;
   private List<ItemPojo> itens;

   public ListaMaterialPojo() {
   }

   public ListaMaterialPojo(final ListaMaterial listaMaterial) {
      this.escola = new EscolaPojo(listaMaterial.getEscola());
      this.serie = new SeriePojo(listaMaterial.getSerie());
      this.itens = new ArrayList<>();
      listaMaterial.getItens().forEach(pojo -> this.itens.add(new ItemPojo(pojo)));
   }

   public EscolaPojo getEscola() {
      return this.escola;
   }

   public void setEscola(final EscolaPojo escola) {
      this.escola = escola;
   }

   public SeriePojo getSerie() {
      return this.serie;
   }

   public void setSerie(final SeriePojo serie) {
      this.serie = serie;
   }

   public List<ItemPojo> getItens() {
      return this.itens;
   }

   public void setItens(final List<ItemPojo> itens) {
      this.itens = itens;
   }

   @Override
   public ListaMaterial toModel(final Repository<ListaMaterial> repository) {
      final EscolaReal escolaModel = this.escola.toModel();

      final Serie serieModel = this.serie.toModel();

      final List<Item> models = new ArrayList<>();
      this.itens.forEach(pojo -> models.add(pojo.toModel()));

      return ListaMaterial.create(escolaModel, serieModel, models);
   }

}
