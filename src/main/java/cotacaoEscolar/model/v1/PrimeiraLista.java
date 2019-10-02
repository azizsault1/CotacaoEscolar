package cotacaoEscolar.model.v1;

import java.util.Collections;
import java.util.List;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;

public class PrimeiraLista implements ListaMaterial {

   @Override
   public EscolaReal getEscola() {
      return null;
   }

   @Override
   public Serie getSerie() {
      return null;
   }

   @Override
   public boolean pertenceA(final Escola escola) {
      return false;
   }

   @Override
   public List<Item> getItens() {
      return Collections.emptyList();
   }

   @Override
   public boolean pertenceASerie(final Serie serie) {
      return false;
   }

   @Override
   public void addItem(final Item item) {
   }

   @Override
   public boolean primeiraLista() {
      return true;
   }

}
