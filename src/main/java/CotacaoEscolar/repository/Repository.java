package cotacaoEscolar.repository;

import java.util.Collection;
import java.util.List;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaItem;
import cotacaoEscolar.model.ListaMaterial;

@org.springframework.stereotype.Repository
public interface Repository {

   ListaItem selecionePor(int serie);

   List<DescricaoMaterialEscolar> todasDescricoes();

   Collection<Escola> escolas();

   Collection<ListaMaterial> materiais();

   void add(ListaMaterial novaLista);

}
