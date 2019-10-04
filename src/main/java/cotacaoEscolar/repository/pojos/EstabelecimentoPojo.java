package cotacaoEscolar.repository.pojos;

import java.util.ArrayList;
import java.util.List;

import cotacaoEscolar.model.Estabelecimento;
import cotacaoEscolar.model.Produto;
import cotacaoEscolar.model.listas.ListaProduto;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "estabelecimentos", schemaVersion = "1.0")
public class EstabelecimentoPojo implements ParserToModel<Estabelecimento> {

   @Id
   private String nome;
   private List<ProdutoPojo> produtos;

   public EstabelecimentoPojo() {
   }

   public EstabelecimentoPojo(final Estabelecimento estabelecimento) {
      this.nome = estabelecimento.getNome();
      this.produtos = new ArrayList<>();
      estabelecimento.getProdutos().forEach(produto -> this.produtos.add(new ProdutoPojo(produto)));
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(final String nome) {
      this.nome = nome;
   }

   public List<ProdutoPojo> getProdutos() {
      return this.produtos;
   }

   public void setProdutos(final List<ProdutoPojo> produtos) {
      this.produtos = produtos;
   }

   @Override
   public Estabelecimento toModel() {

      final List<Produto> models = new ArrayList<>();
      this.produtos.forEach(pojo -> models.add(pojo.toModel()));

      final ListaProduto listaProdutos = new ListaProduto(models);
      return Estabelecimento.create(this.nome, listaProdutos);
   }

}
