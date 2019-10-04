package cotacaoEscolar.repository.pojos;

import java.util.ArrayList;
import java.util.List;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.listas.ListaMaterial;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "listaMateriais", schemaVersion = "1.0")
public class ListaMaterialPojo implements ParserToModel<ListaMaterial> {

	@Id
	private EscolaPojo escola;
	private String serie;
	private List<ItemPojo> itens;

	public ListaMaterialPojo() {
	}

	public ListaMaterialPojo(final ListaMaterial listaMaterial) {
		this.escola = new EscolaPojo(listaMaterial.getEscola());
		this.serie = listaMaterial.getSerie();
		this.itens = new ArrayList<>();
		listaMaterial.getItens().forEach(pojo -> this.itens.add(new ItemPojo(pojo)));
	}

	public EscolaPojo getEscola() {
		return this.escola;
	}

	public void setEscola(final EscolaPojo escola) {
		this.escola = escola;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(final String serie) {
		this.serie = serie;
	}

	public List<ItemPojo> getItens() {
		return this.itens;
	}

	public void setItens(final List<ItemPojo> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((escola == null) ? 0 : escola.hashCode());
		result = prime * result + ((serie == null) ? 0 : serie.hashCode());
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
		ListaMaterialPojo other = (ListaMaterialPojo) obj;
		if (escola == null) {
			if (other.escola != null)
				return false;
		} else if (!escola.equals(other.escola))
			return false;
		if (serie == null) {
			if (other.serie != null)
				return false;
		} else if (!serie.equals(other.serie))
			return false;
		return true;
	}

	@Override
	public ListaMaterial toModel() {
		final Escola escolaModel = this.escola.toModel();

		final List<Item> models = new ArrayList<>();
		this.itens.forEach(pojo -> models.add(pojo.toModel()));

		return new ListaMaterial(escolaModel, this.serie, models);
	}
}
