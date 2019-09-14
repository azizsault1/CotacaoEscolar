package cotacaoEscolar.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.ListaItem;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.service.ServicoListaMaterial;

@Service
public class ServicoListaMaterialLocal implements ServicoListaMaterial {

   private final List<ListaMaterial> listaMaterialEscolar = new ArrayList<>();

   public ServicoListaMaterialLocal() {
   }

   @Override
   public List<ListaMaterial> selecionePor(final Escola escola) {
      return this.listaMaterialEscolar.stream().filter(material -> material.pertenceA(escola)).collect(Collectors.toList());
   }

   @Override
   public ListaItem selecionePor(final Escola escola, final Integer serie) {
      Optional<ListaMaterial> materialOpt = this.listaMaterialEscolar.stream().filter(material -> material.pertenceA(escola) && material.pertenceA(serie))
            .findFirst();

      if (!materialOpt.isPresent()) {
         final ListaMaterial novaLista = new ListaMaterial(escola, serie, new ListaItem());
         this.listaMaterialEscolar.add(novaLista);
         materialOpt = Optional.of(novaLista);
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

   @Override
   public ServicoListaMaterialLocal adicionar(final Escola escola, final Integer serie, final ListaItem itens) {
      final ListaItem itensEncontrados = this.selecionePor(escola, serie);
      itensEncontrados.adicionar(itens);
      return this;
   }
}
