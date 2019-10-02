package cotacaoEscolar.service;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.ListaEstabelecimento;
import cotacaoEscolar.model.v1.ResultadoCotacao;

@Service
public interface ServicoCotacao {

   ResultadoCotacao cotar(ListaMaterial lista, ListaEstabelecimento estabelecimentos);

}
