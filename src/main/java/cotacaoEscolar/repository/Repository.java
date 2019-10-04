package cotacaoEscolar.repository;

import java.util.ArrayList;
import java.util.List;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.repository.pojos.ParserToModel;

interface Repository<Model> {

   public void salvaSaPorra(Model model) throws FoiNao;

   default List<Model> toModels(final List<ParserToModel<Model>> pojos) {
      final List<Model> models = new ArrayList<>();
      pojos.forEach(pojo -> models.add(this.toModel(pojo)));
      return models;
   }

   default Model toModel(final ParserToModel<Model> pojo) {
      return pojo.toModel();
   }

}
