package cotacaoEscolar.service;

import cotacaoEscolar.model.ListaEstabelecimento;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.ResultadoCotacao;

public interface ServicoCotacao {

   public ResultadoCotacao cotar(ListaMaterial lista, ListaEstabelecimento estabelecimentos);

}
