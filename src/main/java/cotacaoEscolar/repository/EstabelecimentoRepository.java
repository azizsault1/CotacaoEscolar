package cotacaoEscolar.repository;

import java.util.Optional;

import cotacaoEscolar.model.v1.Estabelecimento;
import cotacaoEscolar.model.v1.ListaEstabelecimento;

public interface EstabelecimentoRepository extends Repository<Estabelecimento> {

   ListaEstabelecimento estabelecimentos();

   Optional<Estabelecimento> selecionePor(Estabelecimento estabelecimento);

}
