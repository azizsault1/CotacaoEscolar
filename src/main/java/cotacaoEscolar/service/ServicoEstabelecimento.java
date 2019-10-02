package cotacaoEscolar.service;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.v1.ListaEstabelecimento;

@Service
public interface ServicoEstabelecimento {

   ListaEstabelecimento todos();

}
