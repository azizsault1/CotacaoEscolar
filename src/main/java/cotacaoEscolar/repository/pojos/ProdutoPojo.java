package cotacaoEscolar.repository.pojos;

import java.math.BigDecimal;

import cotacaoEscolar.model.Produto;
import cotacaoEscolar.model.v1.ProdutoImpl;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "produtos", schemaVersion = "1.0")
public class ProdutoPojo implements ParserToModel<ProdutoImpl> {

	@Id
	private DescricaoMaterialEscolarPojo materialEscolar;
	private double valor;
	private Integer quantidadeEstoque;

	public ProdutoPojo() {
	}

	public ProdutoPojo(final Produto produto) {
		this.materialEscolar = new DescricaoMaterialEscolarPojo(produto.getDescricao());
		this.valor = produto.getValor().doubleValue();
		this.quantidadeEstoque = produto.getQuantidade();
	}

	public DescricaoMaterialEscolarPojo getMaterialEscolar() {
		return this.materialEscolar;
	}

	public void setMaterialEscolar(final DescricaoMaterialEscolarPojo materialEscolar) {
		this.materialEscolar = materialEscolar;
	}

	public double getValor() {
		return this.valor;
	}

	public void setValor(final double valor) {
		this.valor = valor;
	}

	public Integer getQuantidadeEstoque() {
		return this.quantidadeEstoque;
	}

	public void setQuantidadeEstoque(final Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((materialEscolar == null) ? 0 : materialEscolar.hashCode());
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
		ProdutoPojo other = (ProdutoPojo) obj;
		if (materialEscolar == null) {
			if (other.materialEscolar != null)
				return false;
		} else if (!materialEscolar.equals(other.materialEscolar))
			return false;
		return true;
	}

	@Override
	public ProdutoImpl toModel() {
		return ProdutoImpl.create(this.materialEscolar.getDescricao(), BigDecimal.valueOf(this.valor),
				this.quantidadeEstoque);
	}

}
