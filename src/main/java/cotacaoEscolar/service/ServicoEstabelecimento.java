package cotacaoEscolar.service;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.listas.ListaEstabelecimento;

@Service
public interface ServicoEstabelecimento {

   ListaEstabelecimento todos();

}
