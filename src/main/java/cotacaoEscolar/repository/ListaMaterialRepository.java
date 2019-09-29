package cotacaoEscolar.repository;

import java.util.Collection;

import cotacaoEscolar.model.listas.ListaMaterial;

public interface ListaMaterialRepository extends Repository<ListaMaterial> {

   Collection<ListaMaterial> materiais();

}
