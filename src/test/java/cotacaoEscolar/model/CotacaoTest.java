package cotacaoEscolar.model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import cotacaoEscolar.model.v1.Cotacao;
import cotacaoEscolar.model.v1.Item;
import cotacaoEscolar.model.v1.Produto;

public class CotacaoTest {

   @Test
   public void DadoMaisItensDoQueAQuantidadeEmEstoqueDeveAdicionarObservacao() {
      final Item item = Mockito.mock(Item.class);
      final Produto produto = Mockito.mock(Produto.class);

      Mockito.when(item.getQuantidade()).thenReturn(15);
      Mockito.when(produto.getQuantidade()).thenReturn(10);

      final Cotacao cotacao = new Cotacao(item, produto);
      final String resultado = cotacao.getObservacao();
      final String observacaoEsperada = "Infelizmente não possuímos a quantidade desejada:15, apenas possuímos:10 para critério de cotação, o valor total considera como se tivéssmos todos os produtos.";
      Assert.assertEquals(observacaoEsperada, resultado);
   }

   @Test
   public void DadoMenosItensDoQueAQuantidadeEmEstoqueNaoSeDeveAdicionarObservacao() {
      final Item item = Mockito.mock(Item.class);
      final Produto produto = Mockito.mock(Produto.class);

      Mockito.when(item.getQuantidade()).thenReturn(5);
      Mockito.when(produto.getQuantidade()).thenReturn(10);

      final Cotacao cotacao = new Cotacao(item, produto);
      final String resultado = cotacao.getObservacao();
      Assert.assertTrue(resultado.isEmpty());
   }

}
