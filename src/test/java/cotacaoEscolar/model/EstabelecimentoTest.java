package cotacaoEscolar.model;

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
      this.estabelecimento = new Estabelecimento("Estabelecimento1", this.produtos);
   }

   @Test
   public void testCotar() {
      final ListaMaterial lista = Mockito.mock(ListaMaterial.class);
      final Item item1 = new Item("Item1", 3);
      final Item item2 = new Item("Item2", 5);
      final Item item3 = new Item("Item3", 7);
      final List<Item> itens = Arrays.asList(item1, item2, item3);
      final Produto produto1 = new Produto("Produto1", BigDecimal.valueOf(2));
      final Produto produto3 = new Produto("Produto3", BigDecimal.valueOf(30));
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
      Assert.assertEquals(new DescricaoMaterialEscolar("Item1"), encontrado1.getMaterialEscolar());
      Assert.assertEquals(3, encontrado1.getQuantidade());
      Assert.assertEquals(BigDecimal.valueOf(6), encontrado1.getValorTotal());
      Assert.assertEquals(BigDecimal.valueOf(2), encontrado1.getValorUnitario());

      // Produto 2 Nao encontrado
      final List<Item> itemNaoEncontrado = resultado.getNaoEncontrados();
      Assert.assertEquals(1, itemNaoEncontrado.size());
      Assert.assertEquals(new DescricaoMaterialEscolar("Item2"), itemNaoEncontrado.get(0).getMaterialEscolar());

      // Produto3 encontrado
      final Cotacao encontrado3 = encontrados.get(1);
      Assert.assertEquals(new DescricaoMaterialEscolar("Item3"), encontrado3.getMaterialEscolar());
      Assert.assertEquals(7, encontrado3.getQuantidade());
      Assert.assertEquals(BigDecimal.valueOf(210), encontrado3.getValorTotal());
      Assert.assertEquals(BigDecimal.valueOf(30), encontrado3.getValorUnitario());

   }

}
