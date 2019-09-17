package cotacaoEscolar.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cotacaoEscolar.model.listas.ListaItem;

public class ResultadoCotacaoEstabelecimento implements Comparable<ResultadoCotacaoEstabelecimento> {

   private final String nome;
   private final List<Cotacao> produtosEncontrados;
   private final ListaItem itensNaoEncontrados;
   private BigDecimal total;

   public ResultadoCotacaoEstabelecimento(final String nome) {
      this.produtosEncontrados = new ArrayList<>();
      this.itensNaoEncontrados = new ListaItem();
      this.nome = nome;
      this.total = BigDecimal.ZERO;
   }

   public void encontrei(final Item item, final Produto produto) {
      final Cotacao cotacao = new Cotacao(item, produto);
      this.produtosEncontrados.add(cotacao);
      this.total = this.total.add(cotacao.getValorTotal());
   }

   public void naoEncontrei(final Item item) {
      this.itensNaoEncontrados.adicionar(item);
   }

   public String getNome() {
      return this.nome;
   }

   public BigDecimal getTotal() {
      return this.total;
   }

   @Override
   public int compareTo(final ResultadoCotacaoEstabelecimento o) {
      return this.total.compareTo(o.total);
   }

   public List<Cotacao> getEncontrados() {
      return this.produtosEncontrados;
   }

   public ListaItem getNaoEncontrados() {
      return this.itensNaoEncontrados;
   }

   @Override
   public String toString() {
      return "ResultadoCotacaoEstabelecimento [nome=" + this.nome + ", produtosEncontrados=" + this.produtosEncontrados + ", itensNaoEncontrados="
            + this.itensNaoEncontrados + ", total=" + this.total + "]";
   }
}
