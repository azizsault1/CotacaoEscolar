package CotacaoEscolar.item.model;

/**
 * Escreva a descrição da classe Item aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class Item implements Comparable<Item> {
   private final DescricaoMaterialEscolar materialEscolar;
   private final int quantidade;

   /**
    * COnstrutor para objetos da classe Item
    */
   public Item(final DescricaoMaterialEscolar descricaoMaterialEscolar, final int quantidade) {
      this.materialEscolar = descricaoMaterialEscolar;
      this.quantidade = quantidade;
   }

   public Item(final String descricaoMaterialEscolar, final int quantidade) {
      this(new DescricaoMaterialEscolar(descricaoMaterialEscolar), quantidade);
   }

   public int getQuantidade() {
      return this.quantidade;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + ((this.materialEscolar == null) ? 0 : this.materialEscolar.hashCode());
      result = (prime * result) + this.quantidade;
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
      final Item other = (Item) obj;
      if (this.materialEscolar == null) {
         if (other.materialEscolar != null) {
            return false;
         }
      } else if (!this.materialEscolar.equals(other.materialEscolar)) {
         return false;
      }
      if (this.quantidade != other.quantidade) {
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
      return this.materialEscolar.compareTo(o.materialEscolar);
   }

}
