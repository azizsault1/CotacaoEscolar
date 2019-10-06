package cotacaoEscolar.model;

import java.util.List;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.v1.Item;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.model.v1.helpers.Manjada;

public interface ListaMaterial extends Manjada<ListaMaterial> {

   public Escola getEscola();

   public Serie getSerie();

   @Override
   public ListaMaterial salvar() throws FoiNao;

   public boolean pertenceA(final Escola escola);

   public List<Item> getItens();

   public boolean pertenceASerie(final Serie serie);

   public void addItem(final Item item);

   public boolean primeiraLista();

}
