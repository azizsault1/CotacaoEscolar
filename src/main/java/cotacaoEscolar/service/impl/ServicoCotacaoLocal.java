package cotacaoEscolar.service.impl;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.ListaEstabelecimento;
import cotacaoEscolar.model.v1.ResultadoCotacao;
import cotacaoEscolar.service.ServicoCotacao;

@Service
public class ServicoCotacaoLocal implements ServicoCotacao {

   @Override
   public ResultadoCotacao cotar(final ListaMaterial lista, final ListaEstabelecimento estabelecimentos) {
      return estabelecimentos.cotar(lista);
   }

}
