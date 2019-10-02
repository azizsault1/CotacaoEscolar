package cotacaoEscolar.repository.pojos;

import cotacaoEscolar.repository.Repository;

public interface ParserToModel<Model> {

   Model toModel(Repository<Model> repository);

}
