package cotacaoEscolar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.Escola;

@Service
public interface ServicoEscola {

   public Collection<Escola> todas();

   public Escola buscar(String escola);

   public void salvar(Escola escola) throws FoiNao;

}
