package cotacaoEscolar.model;

import java.util.Collection;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.model.v1.helpers.ListaMaterialAutoSave;

public interface ListaMateriaisEscolares {
   public Collection<ListaMaterial> todas();

   public Collection<ListaMaterial> selecionePor(final Escola escola);

   public ListaMaterialAutoSave selecionePor(final Escola escola, final Serie serie) throws FoiNao;

   // public static ListaMateriaisEscolares create(final ListaMaterialRepository repository) {
   // return new ListaMateriaisEscolares() {
   //
   // @Override
   // public Collection<ListaMaterial> todas() {
   // return repository.materiais();
   // }
   //
   // @Override
   // public Collection<ListaMaterial> selecionePor(final Escola escola) {
   // final Collection<ListaMaterial> listaMaterialEscolar = this.protegerDoBanco(repository.materiais());
//            //@formatter:off
//            final Collection<ListaMaterial> lista = listaMaterialEscolar
//                  .stream()
//                  .filter(material -> material.pertenceA(escola))
//                  .collect(Collectors.toList());
//            //@formatter:on
   // return lista;
   // }
   //
   // @Override
   // public ListaMaterialAutoSave selecionePor(final Escola escola, final Serie serie) throws FoiNao {
   // final Collection<ListaMaterial> listaMaterialEscolar = this.protegerDoBanco(repository.materiais());
   //
//            //@formatter:off
//            final Optional<ListaMaterial> materialOpt = listaMaterialEscolar
//                  .stream()
//                  .filter(material -> material.pertenceA(escola) && material.pertenceASerie(serie))
//                  .findFirst();
//            //@formatter:on
   //
   // final ListaMaterialAutoSave material = ListaMaterialReal.create(repository, escola, serie);
   //
   // if (!materialOpt.isPresent()) {
   // material.save();
   // }
   //
   // return material;
   // }
   //
   // private Collection<ListaMaterial> protegerDoBanco(final Collection<ListaMaterial> materiais) {
   // return materiais == null ? Collections.emptyList() : materiais;
   // }
   // };
   // }

}
