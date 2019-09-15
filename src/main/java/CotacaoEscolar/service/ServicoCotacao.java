package cotacaoEscolar.service;

import cotacaoEscolar.model.ResultadoCotacao;
import cotacaoEscolar.model.listas.ListaEstabelecimento;
import cotacaoEscolar.model.listas.ListaMaterial;

public interface ServicoCotacao {

   public ResultadoCotacao cotar(ListaMaterial lista, ListaEstabelecimento estabelecimentos);

}
