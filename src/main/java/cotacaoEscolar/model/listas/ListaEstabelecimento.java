package cotacaoEscolar.model.listas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cotacaoEscolar.model.Estabelecimento;
import cotacaoEscolar.model.ResultadoCotacao;
import cotacaoEscolar.model.ResultadoCotacaoEstabelecimento;

public class ListaEstabelecimento {

   private final List<Estabelecimento> estabelecimentos;

   public ListaEstabelecimento() {
      this.estabelecimentos = new ArrayList<>();
   }

   public ListaEstabelecimento(final List<Estabelecimento> estabelecimentos) {
      this.estabelecimentos = new ArrayList<>(estabelecimentos);
   }

   public void addAll(final List<Estabelecimento> estabelecimentos) {
      this.estabelecimentos.addAll(estabelecimentos);
   }

   public ResultadoCotacao cotar(final ListaMaterial lista) {
      final List<ResultadoCotacaoEstabelecimento> resultados = new ArrayList<>();

      this.estabelecimentos.forEach(estabelecimento -> resultados.add(estabelecimento.cotar(lista)));

      Collections.sort(resultados);
      return new ResultadoCotacao(resultados);
   }

   public void add(final Estabelecimento estabelecimento) {
      this.estabelecimentos.add(estabelecimento);
   }

}
