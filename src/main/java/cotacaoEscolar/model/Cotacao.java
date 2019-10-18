package cotacaoEscolar.model;

import java.math.BigDecimal;
import java.text.NumberFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Uma cotação sigifica uma cotação de um item em um estabelecimento.")
@JsonSerialize
public class Cotacao {

   private final String descricao;
   private final int quantidade;
   private final BigDecimal valorUnitario;
   private final String observacao;
   private final BigDecimal total;

   public Cotacao(final String descricao, final int quantidadeProcurada, final int quantidadeEncontrada, final BigDecimal valorUnitario) {
      this.descricao = descricao;
      this.quantidade = quantidadeProcurada;
      this.valorUnitario = valorUnitario;
      this.total = this.valorUnitario.multiply(BigDecimal.valueOf(this.quantidade));
      this.observacao = quantidadeProcurada > quantidadeEncontrada ? this.criarObservacao(quantidadeEncontrada) : "";
   }

   public String getObservacao() {
      return this.observacao;
   }

   public BigDecimal getValorUnitario() {
      return this.valorUnitario;
   }

   public String getDescricao() {
      return this.descricao;
   }

   public int getQuantidade() {
      return this.quantidade;
   }

   private String criarObservacao(final int quantidadeEncontrada) {
      return "Infelizmente não possuímos a quantidade desejada:" + this.quantidade + ", apenas possuímos:" + quantidadeEncontrada
            + " para critério de cotação, o valor total considera como se tivéssmos todos os produtos.";
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   @Override
   public String toString() {
      return "Cotacao [descricao=" + this.descricao + ", quantidade=" + this.quantidade + ", valorUnitario=" + this.valorUnitario + ", observacao="
            + this.observacao + "]";
   }

   //@formatter:off
   public String toReport() {
      final StringBuffer report = new StringBuffer("Item: " + this.descricao)
      .append(System.getProperty("line.separator"))
      .append("Quantidade: ").append(this.quantidade).append("    ").append("Total unitário: "+ NumberFormat.getCurrencyInstance().format(this.valorUnitario))
      .append(System.getProperty("line.separator"))
      .append("Valor da cotação: " + NumberFormat.getCurrencyInstance().format(this.total))
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
