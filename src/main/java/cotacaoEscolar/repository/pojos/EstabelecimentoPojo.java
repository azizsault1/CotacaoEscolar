package cotacaoEscolar.repository.pojos;

import java.util.ArrayList;
import java.util.List;

import cotacaoEscolar.model.v1.Estabelecimento;
import cotacaoEscolar.model.v1.ListaProduto;
import cotacaoEscolar.model.v1.Produto;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstabelecimentoPojo other = (EstabelecimentoPojo) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public Estabelecimento toModel() {

		final List<Produto> models = new ArrayList<>();
		this.produtos.forEach(pojo -> models.add(pojo.toModel()));

		final ListaProduto listaProdutos = new ListaProduto(models);
		return Estabelecimento.create(this.nome, listaProdutos);
	}

}
