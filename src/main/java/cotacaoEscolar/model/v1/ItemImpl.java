package cotacaoEscolar.model.v1;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Item;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "Representa um Item na lista de material escolar, que contém uma Descrição e uma quantidade.")
public class ItemImpl implements Comparable<Item>, Item {
   private final DescricaoMaterialEscolar materialEscolar;
   private final int quantidade;

   @JsonCreator
   public ItemImpl(@JsonProperty("materialEscolar") final DescricaoMaterialEscolarImpl descricaoMaterialEscolar,
         @JsonProperty("quantidade") final int quantidade) {
      this.materialEscolar = descricaoMaterialEscolar;
      this.quantidade = quantidade;
   }

   public ItemImpl(final String descricao, final int quantidade) {
      this(DescricaoMaterialEscolarImpl.create(descricao), quantidade);
   }

   @Override
   public DescricaoMaterialEscolar getMaterialEscolar() {
      return this.materialEscolar;
   }

   @Override
   public int getQuantidade() {
      return this.quantidade;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + ((this.materialEscolar == null) ? 0 : this.materialEscolar.hashCode());
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
      final ItemImpl other = (ItemImpl) obj;
      if (this.materialEscolar == null) {
         if (other.materialEscolar != null) {
            return false;
         }
      } else if (!this.materialEscolar.equals(other.materialEscolar)) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return this.materialEscolar.toString();
   }

   @Override
   public int compareTo(final Item o) {
      return this.materialEscolar.compareTo(o.getMaterialEscolar());
   }

   public static ItemImpl create(final String descricao, final int quantidade) {
      return new ItemImpl(descricao, quantidade);
   }

   public static List<ItemImpl> create(final int quantidade) {
      final List<ItemImpl> result = new ArrayList<>();
      for (int i = 0; i < quantidade; i++) {
         result.add(create("Item" + i, i));
      }
      return result;
   }

   //@formatter:off
   @Override
   public String toReport() {
      final StringBuffer report = new StringBuffer("Item: " + this.getMaterialEscolar())
      .append(System.getProperty("line.separator"))
      .append("Quantidade procurada: ").append(this.getQuantidade())
      .append(System.getProperty("line.separator"))
      .append("---")
      .append(System.getProperty("line.separator"));
      return report.toString();
   }

}
