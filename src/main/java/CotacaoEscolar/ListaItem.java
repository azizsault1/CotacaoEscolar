package CotacaoEscolar;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Escreva a descrição da classe ListaItems aqui.
 *
 * @author Aziz
 * @version 1.0
 */
import java.util.List;

import CotacaoEscolar.item.model.Item;

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

   public void adicionarItem(final Item item) {
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
      return this.items.iterator();
   }
}
