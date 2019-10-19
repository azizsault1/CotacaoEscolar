package cotacaoEscolar.model.v1;

import java.util.Collections;
import java.util.List;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.ListaMaterial;

public class ListaMaterialSimples implements ListaMaterial {

   private final Escola escola;
   private final Serie serie;
   private final List<Item> itens;

   private ListaMaterialSimples(final Escola escola, final Serie serie, final List<Item> itens) {
      this.escola = escola;
      this.serie = serie;
      this.itens = itens;
   }

   @Override
   public Escola getEscola() {
      return this.escola;
   }

   @Override
   public Serie getSerie() {
      return this.serie;
   }

   @Override
   public boolean pertenceA(final Escola escola) {
      return this.escola.equals(escola);
   }

   @Override
   public List<Item> getItens() {
      return this.itens;
   }

   @Override
   public boolean pertenceASerie(final Serie serie) {
      return this.serie.equals(serie);
   }

   @Override
   public void addItem(final ItemImpl item) {
      this.itens.add(item);
   }

   public static ListaMaterial create(final Escola escola, final Serie serie, final List<Item> itens) {
      return new ListaMaterialSimples(escola, serie, itens);
   }

   public static ListaMaterial create(final Escola escola, final Serie serie) {
      return new ListaMaterialSimples(escola, serie, Collections.emptyList());
   }
}
