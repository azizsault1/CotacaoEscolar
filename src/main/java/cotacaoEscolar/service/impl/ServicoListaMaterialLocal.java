package cotacaoEscolar.service.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.listas.ListaItem;
import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.repository.Repository;
import cotacaoEscolar.service.ServicoListaMaterial;

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
         final ListaMaterial novaLista = new ListaMaterial(escola, serie, new ListaItem());
         this.repository.add(novaLista);
         materialOpt = Optional.of(novaLista);
      }

      return materialOpt.get();

   }

   @Override
   public void remover(final Escola escola, final Integer serie, final Item item) {
      final ListaItem itens = this.selecionePor(escola, serie).getItens();
      itens.remove(item);
   }

   @Override
   public void adicionar(final Escola escola, final Integer serie, final Item item) {
      final ListaItem itens = this.selecionePor(escola, serie).getItens();
      itens.adicionar(item);
   }

   @Override
   public Collection<Integer> selecioneSeriesPor(final Escola escolaEncontrada) {
      final Collection<ListaMaterial> materiais = this.selecionePor(escolaEncontrada);
      return materiais.stream().map(ListaMaterial::getSerie).collect(Collectors.toList());
   }

}
