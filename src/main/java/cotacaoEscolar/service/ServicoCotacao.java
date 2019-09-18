package cotacaoEscolar.service;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.ResultadoCotacao;
import cotacaoEscolar.model.listas.ListaEstabelecimento;
import cotacaoEscolar.model.listas.ListaMaterial;

@Service
public interface ServicoCotacao {

   ResultadoCotacao cotar(ListaMaterial lista, ListaEstabelecimento estabelecimentos);

}
