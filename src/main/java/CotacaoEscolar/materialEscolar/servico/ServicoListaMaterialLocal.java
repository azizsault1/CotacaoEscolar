package cotacaoEscolar.materialEscolar.servico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import cotacaoEscolar.escola.model.Escola;
import cotacaoEscolar.item.model.Item;
import cotacaoEscolar.item.model.ListaItem;
import cotacaoEscolar.item.service.ServicoItem;
import cotacaoEscolar.materialEscolar.modelo.ListaMaterial;

public class ServicoListaMaterialLocal implements ServicoListaMaterial {

   private List<ListaMaterial> listaMaterialEscolar = new ArrayList<>();

   public ServicoListaMaterialLocal(final List<Escola> escolas, final ServicoItem servicoItem) {
      final ListaMaterial lista1 = new ListaMaterial(escolas.get(0), 1, servicoItem.selecionePor(1));
      final ListaMaterial lista2 = new ListaMaterial(escolas.get(0), 2, servicoItem.selecionePor(2));
      final ListaMaterial lista3 = new ListaMaterial(escolas.get(0), 3, servicoItem.selecionePor(3));
      final ListaMaterial lista4 = new ListaMaterial(escolas.get(1), 3, servicoItem.selecionePor(3));
      final ListaMaterial lista5 = new ListaMaterial(escolas.get(1), 2, servicoItem.selecionePor(2));
      final ListaMaterial lista6 = new ListaMaterial(escolas.get(1), 1, servicoItem.selecionePor(1));
      final ListaMaterial lista7 = new ListaMaterial(escolas.get(2), 2, servicoItem.selecionePor(2));
      final ListaMaterial lista8 = new ListaMaterial(escolas.get(2), 1, servicoItem.selecionePor(1));
      final ListaMaterial lista9 = new ListaMaterial(escolas.get(2), 4, servicoItem.selecionePor(4));
      this.listaMaterialEscolar = Arrays.asList(lista1, lista2, lista3, lista4, lista5, lista6, lista7, lista8, lista9);
   }

   @Override
   public List<ListaMaterial> selecionePor(final Escola escola) {
      return this.listaMaterialEscolar.stream().filter(material -> material.pertenceA(escola)).collect(Collectors.toList());
   }

   @Override
   public ListaItem selecionePor(final Escola escola, final Integer serie) {
      final Optional<ListaMaterial> materialOpt = this.listaMaterialEscolar.stream().filter(material -> material.pertenceA(escola) && material.pertenceA(serie))
            .findFirst();

      if (!materialOpt.isPresent()) {
         return new ListaItem();
      }

      return materialOpt.get().getItens();

   }

   @Override
   public void remover(final Escola escola, final Integer serie, final Item item) {
      final ListaItem itens = this.selecionePor(escola, serie);
      itens.remove(item);
   }

   @Override
   public void adicionar(final Escola escola, final Integer serie, final Item item) {
      final ListaItem itens = this.selecionePor(escola, serie);
      itens.adicionar(item);
   }

}
