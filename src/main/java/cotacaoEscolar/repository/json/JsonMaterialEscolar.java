package cotacaoEscolar.repository.json;

import java.util.ArrayList;
import java.util.List;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolarImpl;
import cotacaoEscolar.repository.DescricaoMaterialEscolarRepository;
import cotacaoEscolar.repository.pojos.DescricaoMaterialEscolarPojo;

public class JsonMaterialEscolar implements DescricaoMaterialEscolarRepository {

   private final JsonRepository repository;

   public JsonMaterialEscolar(final JsonRepository repository) {
      this.repository = repository;
   }

   @Override
   public List<DescricaoMaterialEscolarImpl> meDaTudo() {
      final List<DescricaoMaterialEscolarPojo> pojos = this.repository.pegaAPorraToda(DescricaoMaterialEscolarPojo.class);
      return this.toModels(new ArrayList<>(pojos));
   }

   @Override
   public DescricaoMaterialEscolar selecionarPor(final DescricaoMaterialEscolarImpl descricao) {
      return this.toModel(this.repository.pegaEssaCaralha(descricao.getDescricao(), DescricaoMaterialEscolarPojo.class));
   }

   @Override
   public DescricaoMaterialEscolar salvaSaPorra(final DescricaoMaterialEscolarImpl descricaoMaterialEscolar) throws FoiNao {
      try {
         this.repository.salvar(new DescricaoMaterialEscolarPojo(descricaoMaterialEscolar.getDescricao()));
         return descricaoMaterialEscolar;
      } catch (final Exception e) {
         throw new FoiNao("Porra vei, consegui salvar a descricao nao", e);
      }

   }

}
