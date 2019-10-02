package cotacaoEscolar.repository;

import java.util.Collection;

import cotacaoEscolar.model.Escola;

public interface EscolaRepository extends Repository<Escola> {

   Collection<Escola> escolas();

}
