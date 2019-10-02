package cotacaoEscolar.model.listas;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import cotacaoEscolar.model.v1.Item;
import cotacaoEscolar.model.v1.ListaProduto;
import cotacaoEscolar.model.v1.Produto;

public class ListaProdutoTest {

   private final ListaProduto produtos;

   public ListaProdutoTest() {
      this.produtos = new ListaProduto();
      this.produtos.add(Produto.create("Produto1", BigDecimal.valueOf(2), Integer.valueOf(5)));
      this.produtos.add(Produto.create("Produto2", BigDecimal.valueOf(4), Integer.valueOf(5)));
   }

   @Test
   public void testQueroProduto1() {
      final Item item1 = new Item("Produto1", 1);
      final Optional<Produto> produto = this.produtos.quero(item1);
      Assert.assertTrue(produto.isPresent());
      Assert.assertEquals(BigDecimal.valueOf(2), produto.get().getValor());
   }

   @Test
   public void testQueroProduto3() {
      final Item item1 = new Item("Produto3", 1);
      final Optional<Produto> produto = this.produtos.quero(item1);
      Assert.assertFalse(produto.isPresent());
   }

   @Test
   public void ESeRepetirOProduto() {
      this.produtos.add(Produto.create("Produto2", BigDecimal.valueOf(2), Integer.valueOf(5)));
      final Item item = new Item("Produto2", 1);
      final Optional<Produto> produto = this.produtos.quero(item);
      Assert.assertTrue(produto.isPresent());
      Assert.assertEquals(BigDecimal.valueOf(4), produto.get().getValor());
   }

}
