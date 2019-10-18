package cotacaoEscolar.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.ItemImpl;
import cotacaoEscolar.model.v1.ListaMaterialReal;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.model.v1.helpers.ListaMaterialAutoSave;
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
   public ListaMaterialAutoSave selecionePor(final Escola escola, final Serie serie) throws FoiNao {
      final Collection<ListaMaterial> listaMaterialEscolar = this.protegerDoBanco(this.repository.materiais());

      //@formatter:off
      final Optional<ListaMaterial> materialOpt = listaMaterialEscolar
            .stream()
            .filter(material -> material.pertenceA(escola) && material.pertenceASerie(serie))
            .findFirst();
      //@formatter:on

      final ListaMaterialAutoSave materialNovo = ListaMaterialReal.create(this.repository, escola, serie);
      if (!materialOpt.isPresent()) {
         materialNovo.save();
      }
      return materialNovo;

   }

   @Override
   public void remover(final Escola escola, final Serie serie, final ItemImpl item) throws FoiNao {
      final List<ItemImpl> itens = this.selecionePor(escola, serie).getItens();
      itens.remove(item);
   }

   @Override
   public void adicionar(final Escola escola, final Serie serie, final ItemImpl item) throws FoiNao {
      final List<ItemImpl> itens = this.selecionePor(escola, serie).getItens();
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

}
