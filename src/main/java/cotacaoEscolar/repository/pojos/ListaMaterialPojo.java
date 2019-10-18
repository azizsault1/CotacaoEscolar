package cotacaoEscolar.repository.pojos;

import java.util.ArrayList;
import java.util.List;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.ItemImpl;
import cotacaoEscolar.model.v1.Serie;
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
   public ListaMaterial toModel() {
      final Escola escolaModel = this.escola.toModel();

      final Serie serieModel = this.serie.toModel();

      final List<ItemImpl> models = new ArrayList<>();
      this.itens.forEach(pojo -> models.add(pojo.toModel()));

      return ListaMaterial.create(escolaModel, serieModel, models);
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + ((this.escola == null) ? 0 : this.escola.hashCode());
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
      final ListaMaterialPojo other = (ListaMaterialPojo) obj;
      if (this.escola == null) {
         if (other.escola != null) {
            return false;
         }
      } else if (!this.escola.equals(other.escola)) {
         return false;
      }
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
