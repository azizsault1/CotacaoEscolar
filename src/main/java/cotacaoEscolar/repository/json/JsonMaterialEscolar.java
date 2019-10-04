package cotacaoEscolar.repository.json;

import java.util.ArrayList;
import java.util.List;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.repository.DescricaoMaterialEscolarRepository;
import cotacaoEscolar.repository.pojos.DescricaoMaterialEscolarPojo;

public class JsonMaterialEscolar implements DescricaoMaterialEscolarRepository {

   private final JsonRepository repository;

   public JsonMaterialEscolar(final JsonRepository repository) {
      this.repository = repository;
   }

   @Override
   public List<DescricaoMaterialEscolar> meDaTudo() {
      final List<DescricaoMaterialEscolarPojo> pojos = this.repository.pegaAPorraToda(DescricaoMaterialEscolarPojo.class);
      return this.toModels(new ArrayList<>(pojos));
   }

   @Override
   public DescricaoMaterialEscolar selecionarPor(final DescricaoMaterialEscolar descricao) {
      return this.toModel(this.repository.pegaEssaCaralha(descricao.getDescricao(), DescricaoMaterialEscolarPojo.class));
   }

   @Override
   public void salvaSaPorra(final DescricaoMaterialEscolar descricaoMaterialEscolar) throws FoiNao {
      try {
         this.repository.salvar(new DescricaoMaterialEscolarPojo(descricaoMaterialEscolar.getDescricao()));
      } catch (final Exception e) {
         throw new FoiNao("Porra vei, consegui salvar a descricao nao", e);
      }

   }

}
