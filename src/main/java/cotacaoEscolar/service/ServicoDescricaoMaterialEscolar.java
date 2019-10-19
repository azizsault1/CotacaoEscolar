package cotacaoEscolar.service;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolarImpl;

public interface ServicoDescricaoMaterialEscolar {

   DescricaoMaterialEscolar selecionarPor(String materialEscolar);

   void salvar(DescricaoMaterialEscolarImpl descricaoMaterial) throws FoiNao;

}
