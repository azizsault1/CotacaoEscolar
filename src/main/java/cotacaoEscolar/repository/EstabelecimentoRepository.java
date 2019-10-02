package cotacaoEscolar.repository;

import cotacaoEscolar.model.Estabelecimento;
import cotacaoEscolar.model.listas.ListaEstabelecimento;

public interface EstabelecimentoRepository extends Repository<Estabelecimento> {

   ListaEstabelecimento estabelecimentos();

}
