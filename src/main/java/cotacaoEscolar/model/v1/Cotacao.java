package cotacaoEscolar.model.v1;

import java.math.BigDecimal;
import java.text.NumberFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Uma cotação sigifica uma cotação de um item em um estabelecimento.")
@JsonSerialize
public class Cotacao {

   private final ItemImpl item;
   private final Produto produto;
   private final String observacao;

   public Cotacao(final ItemImpl item, final Produto produto) {
      this.item = item;
      this.produto = produto;
      this.observacao = item.getQuantidade() > produto.getQuantidade() ? this.criarObservacao() : "";
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

   public String criarObservacao() {
      return "Infelizmente não possuímos a quantidade desejada:" + this.item.getQuantidade() + ", apenas possuímos:" + this.produto.getQuantidade()
            + " para critério de cotação, o valor total considera como se tivéssmos todos os produtos.";
   }

   public String getObservacao() {
      return this.observacao;
   }

   @Override
   public String toString() {
      return "Cotacao [item=" + this.item + ", produto=" + this.produto + "]";
   }

   //@formatter:off
   public String toReport() {
      final StringBuffer report = new StringBuffer("Item: " + this.item.getMaterialEscolar())
      .append(System.getProperty("line.separator"))
      .append("Quantidade: ").append(this.getQuantidade()).append("    ").append("Total unitário: "+ NumberFormat.getCurrencyInstance().format(this.getValorUnitario()))
      .append(System.getProperty("line.separator"))
      .append("Valor da cotação: " + NumberFormat.getCurrencyInstance().format(this.getValorTotal()))
      .append(System.getProperty("line.separator"));
      if(!this.observacao.isEmpty()) {
         report.append("Observação:")
         .append(System.getProperty("line.separator"))
         .append("  " + this.observacao)
         .append(System.getProperty("line.separator"));
      }
      report.append("---")
      .append(System.getProperty("line.separator"));
      return report.toString();
   }

}
