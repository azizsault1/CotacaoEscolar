 

import java.util.ArrayList;
/**
 * Escreva a descrição da classe ListaItems aqui.
 *
 * @author Aziz
 * @version 1.0
 */
import java.util.List;

public class ListaItem {
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
   private final List<Item> items;

   /**
    * COnstrutor para objetos da classe ListaItems
    */
   public ListaItem() {
      // inicializa variáveis de instância
      this.items = new ArrayList<>();
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
}
