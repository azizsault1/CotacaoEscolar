package cotacaoEscolar.model.v1;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.Produto;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "Representa um produto que é disponibilizado em um estabelecimento.")
public class ProdutoImpl implements Produto {
   private final DescricaoMaterialEscolar materialEscolar;
   private final BigDecimal valor;
   private final Integer quantidadeEstoque;

   private ProdutoImpl(final DescricaoMaterialEscolar descricao, final BigDecimal valor, final Integer quantidade) {
      this.materialEscolar = descricao;
      this.valor = valor;
      this.quantidadeEstoque = quantidade;
   }

   @Override
   public String getDescricao() {
      return this.materialEscolar.getDescricao();
   }

   public boolean equivale(final Item item) {
      final boolean result = this.materialEscolar.equals(item.getMaterialEscolar());
      return result;
   }

   @Override
   public BigDecimal getValor() {
      return this.valor;
   }

   @Override
   public Integer getQuantidade() {
      return this.quantidadeEstoque;
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
      final ProdutoImpl other = (ProdutoImpl) obj;
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

   public static ProdutoImpl create(final DescricaoMaterialEscolar materialEscolar, final BigDecimal valor, final Integer quantidade) {
      if (materialEscolar == null) {
         throw new IllegalError("Descricao do material nao pode ser nulo.");
      }

      validar(valor, quantidade);
      return new ProdutoImpl(materialEscolar, valor, quantidade);
   }

   @JsonCreator
   public static ProdutoImpl create(@JsonProperty("descricao") final String descricao, @JsonProperty("valor") final BigDecimal valor,
         @JsonProperty("quantidade") final Integer quantidade) {
      if ((descricao == null) || descricao.trim().isEmpty()) {
         throw new IllegalError("A descricao do material escolar nao pode ser branco ou nulo.");
      }
      validar(valor, quantidade);
      return new ProdutoImpl(DescricaoMaterialEscolarImpl.create(descricao), valor, quantidade);
   }

   private static void validar(final BigDecimal valor, final Integer quantidade) {
      if (valor == null) {
         throw new IllegalError("Você precisa definir uma quantidade");
      }
      if (valor.intValue() <= 0) {
         throw new IllegalError("O valor de um prduto não poder ser menor ou igual a zero.");
      }
   }

}
