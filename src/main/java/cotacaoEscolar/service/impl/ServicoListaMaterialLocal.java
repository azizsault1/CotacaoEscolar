package cotacaoEscolar.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.repository.ListaMaterialRepository;
import cotacaoEscolar.service.ServicoListaMaterial;

@Service
public class ServicoListaMaterialLocal implements ServicoListaMaterial {

   private final ListaMaterialRepository repository;

   public ServicoListaMaterialLocal(final ListaMaterialRepository repository) {
      this.repository = repository;
   }

   @Override
   public Collection<ListaMaterial> selecionePor(final Escola escola) {
      final Collection<ListaMaterial> listaMaterialEscolar = this.repository.materiais();
      return listaMaterialEscolar.stream().filter(material -> material.pertenceA(escola)).collect(Collectors.toList());
   }

   @Override
   public ListaMaterial selecionePor(final Escola escola, final String serie) {
      final Collection<ListaMaterial> listaMaterialEscolar = this.repository.materiais();
      Optional<ListaMaterial> materialOpt = listaMaterialEscolar.stream().filter(material -> material.pertenceA(escola) && material.pertenceASerie(serie))
            .findFirst();

      if (!materialOpt.isPresent()) {
         materialOpt = Optional.of(this.salvar(escola, serie));
      }

      return materialOpt.get();

   }

   @Override
   public ListaMaterial salvar(final Escola escola, final String serie) {
      final ListaMaterial novaLista = new ListaMaterial(escola, serie, new ArrayList<>());
      this.repository.salvaSaPorra(novaLista);
      return novaLista;
   }

   @Override
   public void remover(final Escola escola, final String serie, final Item item) {
      final List<Item> itens = this.selecionePor(escola, serie).getItens();
      itens.remove(item);
   }

   @Override
   public void adicionar(final Escola escola, final String serie, final Item item) {
      final List<Item> itens = this.selecionePor(escola, serie).getItens();
      itens.add(item);
   }

   @Override
   public Collection<String> selecioneSeriesPor(final Escola escolaEncontrada) {
      final Collection<ListaMaterial> materiais = this.selecionePor(escolaEncontrada);
      return materiais.stream().map(ListaMaterial::getSerie).collect(Collectors.toList());
   }

}
