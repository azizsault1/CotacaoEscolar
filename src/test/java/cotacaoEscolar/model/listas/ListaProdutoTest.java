package cotacaoEscolar.model.listas;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.v1.ItemImpl;
import cotacaoEscolar.model.v1.ListaProduto;
import cotacaoEscolar.model.v1.ProdutoImpl;

public class ListaProdutoTest {

   private final ListaProduto produtos;

   public ListaProdutoTest() {
      this.produtos = new ListaProduto();
      this.produtos.add(ProdutoImpl.create("Produto1", BigDecimal.valueOf(2), Integer.valueOf(5)));
      this.produtos.add(ProdutoImpl.create("Produto2", BigDecimal.valueOf(4), Integer.valueOf(5)));
   }

   @Test
   public void testQueroProduto1() {
      final Item item1 = new ItemImpl("Produto1", 1);
      final Optional<ProdutoImpl> produto = this.produtos.quero(item1);
      Assert.assertTrue(produto.isPresent());
      Assert.assertEquals(BigDecimal.valueOf(2), produto.get().getValor());
   }

   @Test
   public void testQueroProduto3() {
      final Item item1 = new ItemImpl("Produto3", 1);
      final Optional<ProdutoImpl> produto = this.produtos.quero(item1);
      Assert.assertFalse(produto.isPresent());
   }

   @Test
   public void ESeRepetirOProduto() {
      this.produtos.add(ProdutoImpl.create("Produto2", BigDecimal.valueOf(2), Integer.valueOf(5)));
      final Item item = new ItemImpl("Produto2", 1);
      final Optional<ProdutoImpl> produto = this.produtos.quero(item);
      Assert.assertTrue(produto.isPresent());
      Assert.assertEquals(BigDecimal.valueOf(4), produto.get().getValor());
   }

}
