package cotacaoEscolar.repository;

import java.util.Collection;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolarImpl;

public interface DescricaoMaterialEscolarRepository extends Repository<DescricaoMaterialEscolarImpl> {

   Collection<DescricaoMaterialEscolarImpl> meDaTudo();

   DescricaoMaterialEscolar selecionarPor(DescricaoMaterialEscolarImpl create);

}
