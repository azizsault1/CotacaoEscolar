package cotacaoEscolar.model;

import java.util.Collection;

import cotacaoEscolar.model.v1.DescricaoMaterialEscolarImpl;

public interface DescricoesMaterialEscolar {

   Collection<DescricaoMaterialEscolarImpl> todasDescricoes();

}