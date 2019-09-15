package cotacaoEscolar.model.listas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cotacaoEscolar.model.Item;

public class ListaItem implements Iterable<Item> {
   private final List<Item> itens;

   public ListaItem() {
      this.itens = new ArrayList<>();
   }

   public ListaItem(final Item... itens) {
      this();
      for (final Item item : itens) {
         this.adicionar(item);
      }
   }

   public void adicionar(final Item item) {
      this.itens.add(item);
   }

   public List<Item> getItens() {
      return this.itens;
   }

   @Override
   public Iterator<Item> iterator() {
      final List<Item> result = new ArrayList<>(this.itens);
      Collections.sort(result);
      return result.iterator();
   }

   public void remove(final Item item) {
      this.itens.remove(item);
   }

}
