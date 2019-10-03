package cotacaoEscolar.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.Escola;

@Service
public interface ServicoEscola {

   public Optional<Escola> buscar(String escola);

   public void salvar(Escola escola);

}
