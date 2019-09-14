package cotacaoEscolar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.Escola;

@Service
public interface ServicoEscola {

   public List<Escola> todas();

}
