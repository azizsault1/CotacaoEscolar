package cotacaoEscolar.model;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.model.listas.ListaProduto;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "Establecimento é uma entidade que comercializa materiais escolares como por exemplo uma papelaria.")
@JsonSerialize
public class Estabelecimento {

   private final String nome;
   private final ListaProduto produtos;

   private Estabelecimento(final String nome) {
      this(nome, new ListaProduto());
   }

   private Estabelecimento(final String nome, final ListaProduto produtos) {
      this.produtos = produtos;
      this.nome = nome;
   }

   public String getNome() {
      return this.nome;
   }

   public List<Produto> getProdutos() {
      return this.produtos.getProdutos();
   }

   public ResultadoCotacaoEstabelecimento cotar(final ListaMaterial lista) {
      final ResultadoCotacaoEstabelecimento resultado = new ResultadoCotacaoEstabelecimento(this.nome);

      lista.getItens().forEach(item -> {
         final Optional<Produto> produtoOpt = this.produtos.quero(item);

         if (!produtoOpt.isPresent()) {
            resultado.naoEncontrei(item);
         } else {
            final Produto produto = produtoOpt.get();
            final Cotacao cotacao = new Cotacao(item.getMaterialEscolar().getDescricao(), item.getQuantidade(), produto.getQuantidade(), produto.getValor());
            resultado.encontrei(cotacao);
         }

      });
      return resultado;
   }

   public void adicioneMaisUmProdutoAe(final Produto produto) {
      this.produtos.add(produto);
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + ((this.nome == null) ? 0 : this.nome.hashCode());
      return result;
   }

   @Override
   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (this.getClass() != obj.getClass()) {
         return false;
      }
      final Estabelecimento other = (Estabelecimento) obj;
      if (this.nome == null) {
         if (other.nome != null) {
            return false;
         }
      } else if (!this.nome.equals(other.nome)) {
         return false;
      }
      return true;
   }

   @JsonCreator
   public static Estabelecimento create(@JsonProperty("nome") final String nome) {
      validarNome(nome);
      return new Estabelecimento(nome);
   }

   public static Estabelecimento create(final String nome, final ListaProduto listaProduto) {
      validarNome(nome);

      if (listaProduto == null) {
         throw new IllegalArgumentException("Porra velho, se não quiser passar uma lista de produto, chame: Estabelecimento.create(String)");
      }

      return new Estabelecimento(nome, listaProduto);
   }

   private static void validarNome(final String nome) {
      if ((nome == null) || nome.isEmpty()) {
         throw new IllegalArgumentException("O nome do estabelecimento não pode ser nulo ou branco.");
      }
   }

}
