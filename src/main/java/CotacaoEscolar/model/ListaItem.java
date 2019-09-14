package cotacaoEscolar.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
/**
 * Escreva a descrição da classe ListaItems aqui.
 *
 * @author Aziz
 * @version 1.0
 */
import java.util.List;

public class ListaItem implements Iterable<Item> {
   private final List<Item> itens;

   /**
    * COnstrutor para objetos da classe ListaItems
    */
   public ListaItem(final Item... itens) {
      this.itens = new ArrayList<>();
      for (final Item item : itens) {
         this.itens.add(item);
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

   public void adicionar(final ListaItem itens) {
      this.itens.addAll(itens.getItens());
   }
}
