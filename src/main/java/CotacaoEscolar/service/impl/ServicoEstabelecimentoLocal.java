package cotacaoEscolar.service.impl;

import java.util.List;

import cotacaoEscolar.model.Estabelecimento;
import cotacaoEscolar.model.listas.ListaEstabelecimento;
import cotacaoEscolar.service.ServicoEstabelecimento;

public class ServicoEstabelecimentoLocal implements ServicoEstabelecimento {

   private final ListaEstabelecimento estabelecimentos;

   public ServicoEstabelecimentoLocal(final List<Estabelecimento> estabelecimento) {
      this.estabelecimentos = new ListaEstabelecimento(estabelecimento);
   }

   public ListaEstabelecimento todos() {
      return this.estabelecimentos;
   }

}
