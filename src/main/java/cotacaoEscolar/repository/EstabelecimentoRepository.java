package cotacaoEscolar.repository;

import java.util.Optional;

import cotacaoEscolar.model.Estabelecimento;
import cotacaoEscolar.model.listas.ListaEstabelecimento;

public interface EstabelecimentoRepository extends Repository<Estabelecimento> {

   ListaEstabelecimento estabelecimentos();

   Optional<Estabelecimento> selecionePor(Estabelecimento estabelecimento);

}
