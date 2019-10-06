package cotacaoEscolar.model.v1;

import java.util.ArrayList;
/**
 * Escreva a descrição da classe ListaProdutos aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
import java.util.List;
import java.util.Optional;

public class ListaProduto {
   private final List<Produto> produtos;

   public ListaProduto() {
      this.produtos = new ArrayList<>();
   }

   public ListaProduto(final Produto... produtos) {
      this.produtos = new ArrayList<>();
      for (final Produto produto : produtos) {
         this.produtos.add(produto);
      }
   }

   public ListaProduto(final List<Produto> produtos) {
      this.produtos = produtos;
   }

   public void add(final Produto produto) {
      this.produtos.add(produto);
   }

   public Optional<Produto> quero(final Item item) {
      return this.produtos.stream().filter(produto -> produto.equivale(item)).findAny();
   }

   public List<Produto> getProdutos() {
      return this.produtos;
   }

}
