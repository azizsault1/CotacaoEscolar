package cotacaoEscolar.model;

import java.math.BigDecimal;

/**
 * Escreva a descrição da classe Produto aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class Produto {
   private final DescricaoMaterialEscolar materialEscolar;
   private final BigDecimal valor;

   public Produto(final DescricaoMaterialEscolar descricao, final BigDecimal valor) {
      this.materialEscolar = descricao;
      this.valor = valor;
   }

   public Produto(final String descricao, final BigDecimal valor) {
      this.materialEscolar = new DescricaoMaterialEscolar(descricao);
      this.valor = valor;
   }

   public boolean equivale(final Item item) {
      return this.materialEscolar.equals(item.getMaterialEscolar());
   }

   public BigDecimal getValor() {
      return this.valor;
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
      final Produto other = (Produto) obj;
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
      return "Produto [materialEscolar=" + this.materialEscolar + ", valor=" + this.valor + "]";
   }

}
