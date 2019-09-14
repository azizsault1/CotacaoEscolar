package cotacaoEscolar.model;

import java.util.ArrayList;
import java.util.List;

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

}
