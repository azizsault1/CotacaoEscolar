package cotacaoEscolar.model;

import java.math.BigDecimal;

public class Cotacao {

   private final Item item;
   private final Produto produto;

   public Cotacao(final Item item, final Produto produto) {
      this.item = item;
      this.produto = produto;
   }

   public DescricaoMaterialEscolar getMaterialEscolar() {
      return this.item.getMaterialEscolar();
   }

   public int getQuantidade() {
      return this.item.getQuantidade();
   }

   public BigDecimal getValorUnitario() {
      return this.produto.getValor();
   }

   public BigDecimal getValorTotal() {
      return this.produto.getValor().multiply(BigDecimal.valueOf(this.item.getQuantidade()));
   }

   @Override
   public String toString() {
      return "Cotacao [item=" + this.item + ", produto=" + this.produto + "]";
   }

}
