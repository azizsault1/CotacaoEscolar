package cotacaoEscolar.model;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Instituição pública ou privada destinado ao ensino coletivo.")
public interface Escola extends Comparable<Escola> {

   String getNome();

}
