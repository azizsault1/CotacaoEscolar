package cotacaoEscolar.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;
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

   public static DescricaoMaterialEscolar create(final String descricao) {
      return new DescricaoMaterialEscolar(descricao);
   }

   public static List<DescricaoMaterialEscolar> create(final int quantidade) {
      final List<DescricaoMaterialEscolar> result = new ArrayList<>();
      for (int i = 0; i < quantidade; i++) {
         result.add(create("Material" + i));
      }
      return result;
   }

}
