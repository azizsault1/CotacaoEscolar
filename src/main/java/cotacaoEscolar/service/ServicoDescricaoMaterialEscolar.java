package cotacaoEscolar.service;

import java.util.Collection;

import cotacaoEscolar.model.v1.DescricaoMaterialEscolar;

public interface ServicoDescricaoMaterialEscolar {

   Collection<DescricaoMaterialEscolar> todasDescricoes();

   DescricaoMaterialEscolar selecionarPor(String materialEscolar);

}
