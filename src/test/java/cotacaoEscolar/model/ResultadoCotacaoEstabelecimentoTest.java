package cotacaoEscolar.model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import cotacaoEscolar.model.v1.ItemImpl;
import cotacaoEscolar.model.v1.ProdutoImpl;
import cotacaoEscolar.model.v1.ResultadoCotacaoEstabelecimentoImpl;

public class ResultadoCotacaoEstabelecimentoTest {

   @Test
   public void dado0e200QuandoComparados200TemQueSerMenorQue0() {
      final ResultadoCotacaoEstabelecimentoImpl resultadoCotacao = new ResultadoCotacaoEstabelecimentoImpl("Estabelecimento 1");
      final ResultadoCotacaoEstabelecimentoImpl resultadoCotacao2 = Mockito.mock(ResultadoCotacaoEstabelecimentoImpl.class);

      Mockito.when(resultadoCotacao2.getTotal()).thenReturn(BigDecimal.valueOf(200));

      final int resultado = resultadoCotacao.compareTo(resultadoCotacao2);
      Assert.assertEquals(1, resultado);
   }

   @Test
   public void dado200e0QuandoComparados200TemQueSerMenorQue0() {
      final ResultadoCotacaoEstabelecimentoImpl resultadoCotacao = new ResultadoCotacaoEstabelecimentoImpl("Estabelecimento 1");
      final ResultadoCotacaoEstabelecimentoImpl resultadoCotacao2 = Mockito.mock(ResultadoCotacaoEstabelecimentoImpl.class);
      final Item item = Mockito.mock(ItemImpl.class);
      final Produto produto = Mockito.mock(ProdutoImpl.class);

      Mockito.when(produto.getValor()).thenReturn(BigDecimal.valueOf(100));
      Mockito.when(item.getQuantidade()).thenReturn(2);
      Mockito.when(resultadoCotacao2.getTotal()).thenReturn(BigDecimal.valueOf(0));

      resultadoCotacao.encontrei(item, produto);

      final int resultado = resultadoCotacao.compareTo(resultadoCotacao2);
      Assert.assertEquals(-1, resultado);
   }

   @Test
   public void dado0e0QuandoComparadosTemQueRetornarIguais() {
      final ResultadoCotacaoEstabelecimentoImpl resultadoCotacao = new ResultadoCotacaoEstabelecimentoImpl("Estabelecimento 1");
      final ResultadoCotacaoEstabelecimentoImpl resultadoCotacao2 = Mockito.mock(ResultadoCotacaoEstabelecimentoImpl.class);

      Mockito.when(resultadoCotacao2.getTotal()).thenReturn(BigDecimal.valueOf(0));

      final int resultado = resultadoCotacao.compareTo(resultadoCotacao2);
      Assert.assertEquals(0, resultado);
   }

   @Test
   public void dado200e300QuandoComparadosTemQueRetornar200MenorQue300() {
      final ResultadoCotacaoEstabelecimentoImpl resultadoCotacao = new ResultadoCotacaoEstabelecimentoImpl("Estabelecimento 1");
      final ResultadoCotacaoEstabelecimentoImpl resultadoCotacao2 = Mockito.mock(ResultadoCotacaoEstabelecimentoImpl.class);
      final Item item = Mockito.mock(ItemImpl.class);
      final Produto produto = Mockito.mock(ProdutoImpl.class);

      Mockito.when(produto.getValor()).thenReturn(BigDecimal.valueOf(100));
      Mockito.when(item.getQuantidade()).thenReturn(2);
      Mockito.when(resultadoCotacao2.getTotal()).thenReturn(BigDecimal.valueOf(300));

      resultadoCotacao.encontrei(item, produto);

      final int resultado = resultadoCotacao.compareTo(resultadoCotacao2);
      Assert.assertEquals(-1, resultado);
   }

   @Test
   public void dado200e100QuandoComparadosTemQueRetornar200MenorQue300() {
      final ResultadoCotacaoEstabelecimentoImpl resultadoCotacao = new ResultadoCotacaoEstabelecimentoImpl("Estabelecimento 1");
      final ResultadoCotacaoEstabelecimentoImpl resultadoCotacao2 = Mockito.mock(ResultadoCotacaoEstabelecimentoImpl.class);
      final Item item = Mockito.mock(ItemImpl.class);
      final Produto produto = Mockito.mock(ProdutoImpl.class);

      Mockito.when(produto.getValor()).thenReturn(BigDecimal.valueOf(100));
      Mockito.when(item.getQuantidade()).thenReturn(2);
      Mockito.when(resultadoCotacao2.getTotal()).thenReturn(BigDecimal.valueOf(100));

      resultadoCotacao.encontrei(item, produto);

      final int resultado = resultadoCotacao.compareTo(resultadoCotacao2);
      Assert.assertEquals(1, resultado);
   }

}
