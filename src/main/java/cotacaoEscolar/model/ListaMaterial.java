package cotacaoEscolar.model;

import java.util.List;

import cotacaoEscolar.app.IllegalError;
import cotacaoEscolar.model.v1.Item;
import cotacaoEscolar.model.v1.ListaMaterialReal;
import cotacaoEscolar.model.v1.Manjada;
import cotacaoEscolar.model.v1.PrimeiraLista;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoListaMaterial;

public interface ListaMaterial extends Manjada<ListaMaterial> {

   public Escola getEscola();

   public Serie getSerie();

   public boolean pertenceA(final Escola escola);

   public List<Item> getItens();

   public boolean pertenceASerie(final Serie serie);

   public void addItem(final Item item);

   public boolean primeiraLista();

   static ListaMaterial criarListaVazia() {
      return new PrimeiraLista();
   }

   public static ListaMaterial create(final Escola escola, final Serie serie, final List<Item> itens) {
      if (escola == null) {
         throw new IllegalError("Opps... essa escola não existe.");
      }

      if ((serie == null)) {
         throw new IllegalError("Opps... essa serie não existe.");
      }

      if (itens == null) {
         throw new IllegalError("Me passe uma lista vazia mais não me passe nulo, por favor!!");
      }
      return new ListaMaterialReal(escola, serie, itens);
   }

   public static ListaMaterial meDaUmMaterial(final ServicoEscola servicoEscola, final ServicoListaMaterial servicoListaMaterial) {
      final ListaMaterial material = servicoListaMaterial.meDaUmMaterial();

      if (!material.souNova()) {

         final ListaMaterialReal listaReal = (ListaMaterialReal) material;
         listaReal.addService(servicoEscola, servicoListaMaterial);
      }
      return material;
   }

}
