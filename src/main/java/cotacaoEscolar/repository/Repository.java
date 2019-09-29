package cotacaoEscolar.repository;

import java.util.ArrayList;
import java.util.List;

import cotacaoEscolar.repository.pojos.ParserToModel;

public interface Repository<Model> {

   public void salvaSaPorra(Model model);

   default List<Model> toModels(final List<ParserToModel<Model>> pojos) {
      final List<Model> models = new ArrayList<>();
      pojos.forEach(pojo -> models.add(this.toModel(pojo)));
      return models;
   }

   default Model toModel(final ParserToModel<Model> pojo) {
      return pojo.toModel();
   }

}
