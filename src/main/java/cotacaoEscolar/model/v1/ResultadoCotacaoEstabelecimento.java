package cotacaoEscolar.model.v1;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Representa o resultado de uma coteção de um estabelecimento, com o total da cotação e etc.")
public class ResultadoCotacaoEstabelecimento implements Comparable<ResultadoCotacaoEstabelecimento> {

   private final String nome;
   private final List<Cotacao> produtosEncontrados;
   private final List<Item> itensNaoEncontrados;
   private BigDecimal total;

   public ResultadoCotacaoEstabelecimento(final String nome) {
      this.produtosEncontrados = new ArrayList<>();
      this.itensNaoEncontrados = new ArrayList<>();
      this.nome = nome;
      this.total = BigDecimal.ZERO;
   }

   public void encontrei(final Item item, final Produto produto) {
      final Cotacao cotacao = new Cotacao(item, produto);
      this.produtosEncontrados.add(cotacao);
      this.total = this.total.add(cotacao.getValorTotal());
   }

   public void naoEncontrei(final Item item) {
      this.itensNaoEncontrados.add(item);
   }

   public String getNome() {
      return this.nome;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   @Override
   public int compareTo(final ResultadoCotacaoEstabelecimento o) {
      final BigDecimal totalO = o.getTotal();
      if ((this.total.intValue() == 0) && (totalO.intValue() > 0)) {
         return 1;
      }

      if ((this.total.intValue() > 0) && (totalO.intValue() == 0)) {
         return -1;
      }
      return this.total.compareTo(totalO);
   }

   public List<Cotacao> getEncontrados() {
      return this.produtosEncontrados;
   }

   public List<Item> getNaoEncontrados() {
      return this.itensNaoEncontrados;
   }

   @Override
   public String toString() {
      return "ResultadoCotacaoEstabelecimento [nome=" + this.nome + ", produtosEncontrados=" + this.produtosEncontrados + ", itensNaoEncontrados="
            + this.itensNaoEncontrados + ", total=" + this.total + "]";
   }

   //@formatter:off
   public String toReport() {
      final StringBuffer report = new StringBuffer("-----------------------------------------")
            .append("Estabelecimento: " + this.nome)
            .append(System.getProperty("line.separator"))
            .append("-----------------------------------------")
            .append(System.getProperty("line.separator"))
            .append("Total da cotação: "+ NumberFormat.getCurrencyInstance().format(this.total))
            .append(System.getProperty("line.separator"))
            .append("Itens encontrados:\n");
            if(this.produtosEncontrados.isEmpty()) {
               report.append("NENHUM!")
               .append(System.getProperty("line.separator"));
            } else {
               this.produtosEncontrados.forEach(produto-> report.append(produto.toReport()));
            }
            report.append("Itens não encontrados:\n");
            if(this.itensNaoEncontrados.isEmpty()) {
               report.append("Todos os Itens procurados foram encontrados!")
               .append(System.getProperty("line.separator"));
            } else {
               this.itensNaoEncontrados.forEach(item -> report.append(item.toReport()));
            }
            report.append(System.getProperty("line.separator"));
      return report.toString();

   }
}
