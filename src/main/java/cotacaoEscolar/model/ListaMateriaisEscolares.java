package cotacaoEscolar.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.v1.ListaMaterialReal;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.repository.ListaMaterialRepository;

public interface ListaMateriaisEscolares {
   public Collection<ListaMaterial> todas();

   public Collection<ListaMaterial> selecionePor(final Escola escola);

   public ListaMaterial selecionePor(final Escola escola, final Serie serie) throws FoiNao;

   public static ListaMateriaisEscolares create(final ListaMaterialRepository repository) {
      return new ListaMateriaisEscolares() {

         @Override
         public Collection<ListaMaterial> todas() {
            return repository.materiais();
         }

         @Override
         public Collection<ListaMaterial> selecionePor(final Escola escola) {
            final Collection<ListaMaterial> listaMaterialEscolar = this.protegerDoBanco(repository.materiais());
            //@formatter:off
            final Collection<ListaMaterial> lista = listaMaterialEscolar
                  .stream()
                  .filter(material -> material.pertenceA(escola))
                  .collect(Collectors.toList());
            //@formatter:on
            return lista;
         }

         @Override
         public ListaMaterial selecionePor(final Escola escola, final Serie serie) throws FoiNao {
            final Collection<ListaMaterial> listaMaterialEscolar = this.protegerDoBanco(repository.materiais());

            //@formatter:off
            Optional<ListaMaterial> materialOpt = listaMaterialEscolar
                  .stream()
                  .filter(material -> material.pertenceA(escola) && material.pertenceASerie(serie))
                  .findFirst();
            //@formatter:on

            if (!materialOpt.isPresent()) {
               //@formatter:off
               final ListaMaterial material = ListaMaterialReal
                     .create(repository, escola, serie)
                     .salvar();
               materialOpt = Optional.of(material);
            }

            return materialOpt.get();
         }

         private Collection<ListaMaterial> protegerDoBanco(final Collection<ListaMaterial> materiais) {
            return materiais == null ? Collections.emptyList() : materiais;
         }

      };
   }

}
