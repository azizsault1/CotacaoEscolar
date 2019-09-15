package cotacaoEscolar.model;

import java.util.Optional;

import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.model.listas.ListaProduto;

public class Estabelecimento {

   private final String nome;
   private final ListaProduto produtos;

   public Estabelecimento(final String nome) {
      this(nome, new ListaProduto());
   }

   public Estabelecimento(final String nome, final ListaProduto produtos) {
      this.produtos = produtos;
      this.nome = nome;
   }

   public ResultadoCotacaoEstabelecimento cotar(final ListaMaterial lista) {
      final ResultadoCotacaoEstabelecimento resultado = new ResultadoCotacaoEstabelecimento(this.nome);

      lista.getItens().forEach(item -> {
         final Optional<Produto> produtoOpt = this.produtos.quero(item);

         if (!produtoOpt.isPresent()) {
            resultado.naoEncontrei(item);
         } else {
            resultado.encontrei(item, produtoOpt.get());
         }

      });
      return resultado;
   }
}
