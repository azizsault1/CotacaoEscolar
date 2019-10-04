package cotacaoEscolar.service;

import java.util.Collection;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.DescricaoMaterialEscolar;

public interface ServicoDescricaoMaterialEscolar {

   Collection<DescricaoMaterialEscolar> todasDescricoes();

   DescricaoMaterialEscolar selecionarPor(String materialEscolar);

   void salvar(DescricaoMaterialEscolar descricaoMaterial) throws FoiNao;

}
