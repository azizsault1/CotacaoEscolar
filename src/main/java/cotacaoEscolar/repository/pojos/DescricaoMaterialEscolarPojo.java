package cotacaoEscolar.repository.pojos;

import cotacaoEscolar.model.v1.DescricaoMaterialEscolar;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "descricoes", schemaVersion = "1.0")
public class DescricaoMaterialEscolarPojo implements ParserToModel<DescricaoMaterialEscolar> {

	@Id
	private String descricao;

	public DescricaoMaterialEscolarPojo() {
	}

	public DescricaoMaterialEscolarPojo(final String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		DescricaoMaterialEscolarPojo other = (DescricaoMaterialEscolarPojo) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public DescricaoMaterialEscolar toModel() {
		return DescricaoMaterialEscolar.create(this.descricao);
	}

}
