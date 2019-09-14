package cotacaoEscolar.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Escreva a descrição da classe DescricaoMaterialEscolar aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
@JsonSerialize
public class DescricaoMaterialEscolar implements Comparable<DescricaoMaterialEscolar> {
   private final String descricao;

   public DescricaoMaterialEscolar(final String descricao) {
      this.descricao = descricao;
   }

   public String getDescricao() {
      return this.descricao;
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
