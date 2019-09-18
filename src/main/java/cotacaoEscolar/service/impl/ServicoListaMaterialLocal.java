package cotacaoEscolar.service.impl;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.repository.Repository;
import cotacaoEscolar.service.ServicoListaMaterial;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicoListaMaterialLocal implements ServicoListaMaterial {

   private final Repository repository;

   public ServicoListaMaterialLocal(final Repository repository) {
      this.repository = repository;
   }

   @Override
   public Collection<ListaMaterial> selecionePor(final Escola escola) {
      final Collection<ListaMaterial> listaMaterialEscolar = this.repository.materiais();
      return listaMaterialEscolar.stream().filter(material -> material.pertenceA(escola)).collect(Collectors.toList());
   }

   @Override
   public ListaMaterial selecionePor(final Escola escola, final Integer serie) {
      final Collection<ListaMaterial> listaMaterialEscolar = this.repository.materiais();
      Optional<ListaMaterial> materialOpt = listaMaterialEscolar.stream().filter(material -> material.pertenceA(escola) && material.pertenceA(serie))
            .findFirst();

      if (!materialOpt.isPresent()) {
         final ListaMaterial novaLista = new ListaMaterial(escola, serie, new ArrayList<>());
         this.repository.add(novaLista);
         materialOpt = Optional.of(novaLista);
      }

      return materialOpt.get();

   }

   @Override
   public void remover(final Escola escola, final Integer serie, final Item item) {
      final List<Item> itens = this.selecionePor(escola, serie).getItens();
      itens.remove(item);
   }

   @Override
   public void adicionar(final Escola escola, final Integer serie, final Item item) {
      final List<Item> itens = this.selecionePor(escola, serie).getItens();
      itens.add(item);
   }

   @Override
   public Collection<Integer> selecioneSeriesPor(final Escola escolaEncontrada) {
      final Collection<ListaMaterial> materiais = this.selecionePor(escolaEncontrada);
      return materiais.stream().map(ListaMaterial::getSerie).collect(Collectors.toList());
   }

}
