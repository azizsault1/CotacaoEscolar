package cotacaoEscolar.model;

import java.util.Collection;

import org.springframework.web.bind.annotation.RequestBody;

import cotacaoEscolar.app.exceptions.FoiNao;

public interface Escolas {

   public Collection<Escola> todas();

   public Escola salvar(@RequestBody final Escola escola) throws FoiNao;
}
