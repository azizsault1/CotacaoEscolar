package cotacaoEscolar.model.v1;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;

public class ListaMaterialReal implements ListaMaterial, Serializable {
   private static final long serialVersionUID = 1L;
   private final Escola escola;
   private final Serie serie;
   private final List<Item> itens;

   public ListaMaterialReal(final Escola escola, final Serie serie, final List<Item> itens) {
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
   @JsonIgnore
   public boolean pertenceA(final Escola escola) {
      return this.escola.equals(escola);
   }

   @Override
   public List<Item> getItens() {
      return this.itens;
   }

   @Override
   @JsonIgnore
   public boolean pertenceASerie(final Serie serie) {
      return this.serie.equals(serie);
   }

   @Override
   public void addItem(final Item item) {
      this.itens.add(item);
   }

   @Override
   public boolean primeiraLista() {
      return false;
   }

}