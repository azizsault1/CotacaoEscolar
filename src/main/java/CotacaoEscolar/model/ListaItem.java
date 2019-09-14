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
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
   private final List<Item> items;

   /**
    * COnstrutor para objetos da classe ListaItems
    */
   public ListaItem(final Item... itens) {
      this.items = new ArrayList<>();
      for (final Item item : itens) {
         this.items.add(item);
      }
   }

   public void adicionar(final Item item) {
      this.items.add(item);
   }

   /**
    * Retorna a lista de items
    *
    * @return a soma de x com y
    */
   public List<Item> getItems() {
      return this.items;
   }

   @Override
   public Iterator<Item> iterator() {
      final List<Item> result = new ArrayList<>(this.items);
      Collections.sort(result);
      return result.iterator();
   }

   public void remove(final Item item) {
      this.items.remove(item);
   }

   public void adicionar(final ListaItem itens) {
      this.items.addAll(itens.getItems());
   }
}
