package cotacaoEscolar.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import cotacaoEscolar.model.v1.Cotacao;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolarImpl;
import cotacaoEscolar.model.v1.Estabelecimento;
import cotacaoEscolar.model.v1.ItemImpl;
import cotacaoEscolar.model.v1.ListaMaterialReal;
import cotacaoEscolar.model.v1.ListaProduto;
import cotacaoEscolar.model.v1.ProdutoImpl;
import cotacaoEscolar.model.v1.ResultadoCotacaoEstabelecimento;

public class EstabelecimentoTest {

   private final Estabelecimento estabelecimento;
   private final ListaProduto produtos;

   public EstabelecimentoTest() {
      this.produtos = Mockito.mock(ListaProduto.class);
      this.estabelecimento = Estabelecimento.create("Estabelecimento1", this.produtos);
   }

   @Test
   public void testCotar() {
      final ListaMaterialReal lista = Mockito.mock(ListaMaterialReal.class);
      final ItemImpl item1 = new ItemImpl("Item1", 3);
      final ItemImpl item2 = new ItemImpl("Item2", 5);
      final ItemImpl item3 = new ItemImpl("Item3", 7);
      final List<ItemImpl> itens = Arrays.asList(item1, item2, item3);
      final ProdutoImpl produto1 = ProdutoImpl.create("Produto1", BigDecimal.valueOf(2), Integer.valueOf(5));
      final ProdutoImpl produto3 = ProdutoImpl.create("Produto3", BigDecimal.valueOf(30), Integer.valueOf(5));
      final Optional<ProdutoImpl> optionalTem1 = Optional.of(produto1);
      final Optional<ProdutoImpl> optionalTem3 = Optional.of(produto3);
      final Optional<ProdutoImpl> optionalNaoTem = Optional.empty();

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
      Assert.assertEquals(DescricaoMaterialEscolarImpl.create("Item1"), encontrado1.getMaterialEscolar());
      Assert.assertEquals(3, encontrado1.getQuantidade());
      Assert.assertEquals(BigDecimal.valueOf(6), encontrado1.getValorTotal());
      Assert.assertEquals(BigDecimal.valueOf(2), encontrado1.getValorUnitario());

      // Produto 2 Nao encontrado
      final List<ItemImpl> itemNaoEncontrado = resultado.getNaoEncontrados();
      Assert.assertEquals(1, itemNaoEncontrado.size());
      Assert.assertEquals(DescricaoMaterialEscolarImpl.create("Item2"), itemNaoEncontrado.get(0).getMaterialEscolar());

      // Produto3 encontrado
      final Cotacao encontrado3 = encontrados.get(1);
      Assert.assertEquals(DescricaoMaterialEscolarImpl.create("Item3"), encontrado3.getMaterialEscolar());
      Assert.assertEquals(7, encontrado3.getQuantidade());
      Assert.assertEquals(BigDecimal.valueOf(210), encontrado3.getValorTotal());
      Assert.assertEquals(BigDecimal.valueOf(30), encontrado3.getValorUnitario());

   }

}
