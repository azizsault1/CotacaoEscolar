package cotacaoEscolar.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.model.listas.ListaProduto;

public class EstabelecimentoTest {

   private final Estabelecimento estabelecimento;
   private final ListaProduto produtos;

   public EstabelecimentoTest() {
      this.produtos = Mockito.mock(ListaProduto.class);
      this.estabelecimento = Estabelecimento.create("Logo", "Estabelecimento1", this.produtos);
   }

   @Test
   public void testCotar() {
      final ListaMaterial lista = Mockito.mock(ListaMaterial.class);
      final Item item1 = new Item("Item1", 3);
      final Item item2 = new Item("Item2", 5);
      final Item item3 = new Item("Item3", 7);
      final List<Item> itens = Arrays.asList(item1, item2, item3);
      final Produto produto1 = Produto.create("Produto1", BigDecimal.valueOf(2), Integer.valueOf(5));
      final Produto produto3 = Produto.create("Produto3", BigDecimal.valueOf(30), Integer.valueOf(5));
      final Optional<Produto> optionalTem1 = Optional.of(produto1);
      final Optional<Produto> optionalTem3 = Optional.of(produto3);
      final Optional<Produto> optionalNaoTem = Optional.empty();

      Mockito.when(lista.getItens()).thenReturn(itens);
      Mockito.when(this.produtos.quero(item1)).thenReturn(optionalTem1);
      Mockito.when(this.produtos.quero(item2)).thenReturn(optionalNaoTem);
      Mockito.when(this.produtos.quero(item3)).thenReturn(optionalTem3);

      final ResultadoCotacaoEstabelecimento resultado = this.estabelecimento.cotar(lista);
      Assert.assertEquals("Estabelecimento1", resultado.getNome());

      final List<Cotacao> encontrados = resultado.getEncontrados();
      Assert.assertEquals(2, encontrados.size());

      // Produto 1 encontrado
      final Cotacao encontrado1 = encontrados.get(0);
      final String encontrado1Report = encontrado1.toReport();
      //@formatter:off
      final String esperado1Report = "Item: Item1\n" +
            "Quantidade: 3    Total unitário: R$ 2,00\n" +
            "Valor da cotação: R$ 6,00\n" +
            "---\n";
      assertEquals(esperado1Report, encontrado1Report);

      // Produto 2 Nao encontrado
       final List<Item> itemNaoEncontrado = resultado.getNaoEncontrados();
       Assert.assertEquals(1, itemNaoEncontrado.size());
       final String itemNaoEncontradoReport = itemNaoEncontrado.get(0).toReport();
       final String esperadoReportItemNaoEncontrado = "Item: Item2\n" +
             "Quantidade procurada: 5\n" +
             "---\n";
       assertEquals(esperadoReportItemNaoEncontrado, itemNaoEncontradoReport);

      // Produto3 encontrado
      final Cotacao encontrado3 = encontrados.get(1);
      final String reportEncontrado3 = encontrado3.toReport();
      final String reportEsperado3 = "Item: Item3\n" +
            "Quantidade: 7    Total unitário: R$ 30,00\n" +
            "Valor da cotação: R$ 210,00\n" +
            "Observação:\n" +
            "  Infelizmente não possuímos a quantidade desejada:7, apenas possuímos:5 para critério de cotação, o valor total considera como se tivéssmos todos os produtos.\n" +
            "---\n";
      assertEquals(reportEsperado3, reportEncontrado3);
   }

}
