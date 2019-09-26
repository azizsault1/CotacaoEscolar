package cotacaoEscolar.model;

import java.util.Optional;

import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.model.listas.ListaProduto;
import io.swagger.annotations.ApiModel;

@ApiModel(description = "Establecimento é uma entidade que comercializa materiais escolares como por exemplo uma papelaria.")
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

}
