package cotacaoEscolar.service;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolar;

public interface ServicoDescricaoMaterialEscolar {

   DescricaoMaterialEscolar selecionarPor(String materialEscolar);

   void salvar(DescricaoMaterialEscolar descricaoMaterial) throws FoiNao;

}
