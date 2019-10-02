package cotacaoEscolar.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.Item;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.repository.ListaMaterialRepository;
import cotacaoEscolar.service.ServicoListaMaterial;

public class ServicoListaMaterialLocal implements ServicoListaMaterial {

   private final ListaMaterialRepository repository;

   @Autowired
   public ServicoListaMaterialLocal(final ListaMaterialRepository repository) {
      this.repository = repository;
   }

   @Override
   public Collection<ListaMaterial> selecionePor(final Escola escola) {
      final Collection<ListaMaterial> listaMaterialEscolar = this.protegerDoBanco(this.repository.materiais());
      return listaMaterialEscolar.stream().filter(material -> material.pertenceA(escola)).collect(Collectors.toList());
   }

   @Override
   public ListaMaterial selecionePor(final Escola escola, final Serie serie) {
      final Collection<ListaMaterial> listaMaterialEscolar = this.protegerDoBanco(this.repository.materiais());

      //@formatter:off
      Optional<ListaMaterial> materialOpt = listaMaterialEscolar
            .stream()
            .filter(material -> material.pertenceA(escola) && material.pertenceASerie(serie))
            .findFirst();
      //@formatter:on

      if (!materialOpt.isPresent()) {
         materialOpt = Optional.of(this.salvar(escola, serie));
      }

      return materialOpt.get();

   }

   @Override
   public ListaMaterial salvar(final Escola escola, final Serie serie) {
      final ListaMaterial novaLista = ListaMaterial.create(escola, serie, new ArrayList<>());
      this.repository.salvaSaPorra(novaLista);
      return novaLista;
   }

   @Override
   public void remover(final Escola escola, final Serie serie, final Item item) {
      final List<Item> itens = this.selecionePor(escola, serie).getItens();
      itens.remove(item);
   }

   @Override
   public void adicionar(final Escola escola, final Serie serie, final Item item) {
      final List<Item> itens = this.selecionePor(escola, serie).getItens();
      itens.add(item);
   }

   @Override
   public Collection<Serie> selecioneSeriesPor(final Escola escolaEncontrada) {
      final Collection<ListaMaterial> materiais = this.selecionePor(escolaEncontrada);
      //@formatter:off
      return materiais
               .stream()
               .map(ListaMaterial::getSerie)
               .collect(Collectors.toList());
      //@formatter:on
   }

   private Collection<ListaMaterial> protegerDoBanco(final Collection<ListaMaterial> materiais) {
      return materiais == null ? Collections.emptyList() : materiais;
   }

   @Override
   public ListaMaterial meDaUmMaterial() {
      final Collection<ListaMaterial> materiais = this.repository.materiais();

      final ListaMaterial listaMaterial = materiais.stream().findFirst().orElse(ListaMaterial.criarListaVazia());
      return listaMaterial;
   }

}
