package cotacaoEscolar.repository;

import java.util.Collection;

import cotacaoEscolar.model.ListaMaterial;

public interface ListaMaterialRepository extends Repository<ListaMaterial> {

   Collection<ListaMaterial> materiais();

}
