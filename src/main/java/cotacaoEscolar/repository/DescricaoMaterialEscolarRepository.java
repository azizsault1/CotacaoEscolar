package cotacaoEscolar.repository;

import java.util.Collection;

import cotacaoEscolar.model.DescricaoMaterialEscolar;

public interface DescricaoMaterialEscolarRepository extends Repository<DescricaoMaterialEscolar> {

   Collection<DescricaoMaterialEscolar> meDaTudo();

   DescricaoMaterialEscolar selecionarPor(DescricaoMaterialEscolar create);

}
