package cotacaoEscolar.service.impl;

import cotacaoEscolar.model.ResultadoCotacao;
import cotacaoEscolar.model.listas.ListaEstabelecimento;
import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.service.ServicoCotacao;

public class ServicoCotacaoLocal implements ServicoCotacao {

   @Override
   public ResultadoCotacao cotar(final ListaMaterial lista, final ListaEstabelecimento estabelecimentos) {
      return estabelecimentos.cotar(lista);
   }

}
