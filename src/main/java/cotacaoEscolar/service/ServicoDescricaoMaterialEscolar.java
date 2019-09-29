package cotacaoEscolar.service;

import java.util.Collection;

import cotacaoEscolar.model.DescricaoMaterialEscolar;

public interface ServicoDescricaoMaterialEscolar {

   Collection<DescricaoMaterialEscolar> todasDescricoes();

   DescricaoMaterialEscolar selecionarPor(String materialEscolar);

}
