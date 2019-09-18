package cotacaoEscolar.model.listas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cotacaoEscolar.model.Item;

@JsonSerialize
public class ListaItem implements Iterable<Item> {
   private final List<Item> itens;

   public ListaItem() {
      this.itens = new ArrayList<>();
   }

   @JsonCreator
   public ListaItem(List<Item> itens) {
      this.itens = itens;
   }

   public ListaItem(final Item... item) {
      this();
      for (int i = 0; i < item.length ; i++) {
         this.adicionar(item[i]);
      }
   }

   public void adicionar(final Item item) {
      this.itens.add(item);
   }

   @JsonValue
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

   @Override
   public String toString() {
      return "ListaItem [itens=" + this.itens + "]";
   }

   @JsonIgnore
   public boolean isEmpty() {
      return this.itens.isEmpty();
   }
}
