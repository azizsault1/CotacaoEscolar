package cotacaoEscolar.item.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class DescricaoMaterialEscolar implements Comparable<DescricaoMaterialEscolar> {
   private final String descricao;

   public DescricaoMaterialEscolar(final String descricao) {
      this.descricao = descricao;
   }
   
   public String getDescricao() {
	return descricao;
   }

   @Override
   public String toString() {
      return this.descricao;
   }

   @Override
   public int compareTo(final DescricaoMaterialEscolar o) {
      return this.descricao.compareTo(o.descricao);
   }

}
