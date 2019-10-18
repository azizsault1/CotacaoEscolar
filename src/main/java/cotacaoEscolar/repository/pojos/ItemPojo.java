package cotacaoEscolar.repository.pojos;

import cotacaoEscolar.model.v1.DescricaoMaterialEscolar;
import cotacaoEscolar.model.v1.ItemImpl;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "itens", schemaVersion = "1.0")
public class ItemPojo implements ParserToModel<ItemImpl> {

	@Id
	private DescricaoMaterialEscolarPojo materialEscolar;
	private int quantidade;

	public ItemPojo() {
	}

	public ItemPojo(final ItemImpl item) {
		this.materialEscolar = new DescricaoMaterialEscolarPojo(item.getMaterialEscolar().getDescricao());
		this.quantidade = item.getQuantidade();
	}

	public DescricaoMaterialEscolarPojo getMaterialEscolar() {
		return this.materialEscolar;
	}

	public void setMaterialEscolar(final DescricaoMaterialEscolarPojo materialEscolar) {
		this.materialEscolar = materialEscolar;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(final int quantidade) {
		this.quantidade = quantidade;
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
		ItemPojo other = (ItemPojo) obj;
		if (materialEscolar == null) {
			if (other.materialEscolar != null)
				return false;
		} else if (!materialEscolar.equals(other.materialEscolar))
			return false;
		return true;
	}

	@Override
	public ItemImpl toModel() {
		final DescricaoMaterialEscolar descricaoModel = this.materialEscolar.toModel();

		return ItemImpl.create(descricaoModel.getDescricao(), this.quantidade);
	}

}
