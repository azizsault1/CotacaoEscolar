package cotacaoEscolar.model.listas;

import java.util.ArrayList;
/**
 * Escreva a descrição da classe ListaProdutos aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
import java.util.List;
import java.util.Optional;

import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.Produto;

public class ListaProduto {
   private final List<Produto> produtos;

   /**
    * COnstrutor para objetos da classe ListaProdutos
    */
   public ListaProduto() {
      this.produtos = new ArrayList<>();
   }

   public void add(final Produto produto) {
      this.produtos.add(produto);
   }

   public Optional<Produto> quero(final Item item) {
      return this.produtos.stream().filter(produto -> produto.equivale(item)).findFirst();
   }

}
