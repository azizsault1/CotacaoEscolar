package cotacaoEscolar.model;

import java.util.List;

import cotacaoEscolar.model.v1.ItemImpl;
import cotacaoEscolar.model.v1.Serie;

public interface ListaMaterial {

   public Escola getEscola();

   public Serie getSerie();

   public boolean pertenceA(final Escola escola);

   public List<ItemImpl> getItens();

   public boolean pertenceASerie(final Serie serie);

   public void addItem(final ItemImpl item);

}
