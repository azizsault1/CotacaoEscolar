package cotacaoEscolar.repository;

import java.util.Collection;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.listas.ListaEstabelecimento;
import cotacaoEscolar.model.listas.ListaItem;
import cotacaoEscolar.model.listas.ListaMaterial;

@org.springframework.stereotype.Repository
public interface Repository {

   ListaItem selecionePor(int serie);

   Collection<DescricaoMaterialEscolar> todasDescricoes();

   Collection<Escola> escolas();

   Collection<ListaMaterial> materiais();

   void add(ListaMaterial novaLista);

   ListaEstabelecimento estabelecimentos();

}
