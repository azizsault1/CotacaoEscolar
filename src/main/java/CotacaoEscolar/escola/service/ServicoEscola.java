package cotacaoEscolar.escola.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cotacaoEscolar.escola.model.Escola;

@Service
public interface ServicoEscola {

   public List<Escola> todas();

}
