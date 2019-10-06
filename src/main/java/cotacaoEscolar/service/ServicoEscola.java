package cotacaoEscolar.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.Escola;

@Service
public interface ServicoEscola {

   public Optional<Escola> buscar(String escola);

   public Escola salvar(Escola escola) throws FoiNao;

   public Collection<Escola> todas();

}
