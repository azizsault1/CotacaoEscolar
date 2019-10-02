package cotacaoEscolar.model.v1;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cotacaoEscolar.app.IllegalError;
import io.swagger.annotations.ApiModel;

@JsonSerialize
@ApiModel(description = "A descrição de um material escolar é a descrição ou de um um Produto ou de um Item. Exemplo: \"Lapis de cor.\"")
public class DescricaoMaterialEscolar implements Comparable<DescricaoMaterialEscolar> {
   private final String descricao;

   @JsonCreator
   public DescricaoMaterialEscolar(@JsonProperty("descricao") final String descricao) {
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
      if ((descricao == null) || descricao.trim().isEmpty()) {
         throw new IllegalError("A descrição do material escolar não pode ser vazia.");
      }

      return new DescricaoMaterialEscolar(descricao);
   }

   public static List<DescricaoMaterialEscolar> create(final int quantidade) {
      final List<DescricaoMaterialEscolar> result = new ArrayList<>();
      for (int i = 0; i < quantidade; i++) {
         result.add(create("Material" + i));
      }
      return result;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + ((this.descricao == null) ? 0 : this.descricao.hashCode());
      return result;
   }

   @Override
   public boolean equals(final Object obj) {

      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (this.getClass() != obj.getClass()) {
         return false;
      }
      final DescricaoMaterialEscolar other = (DescricaoMaterialEscolar) obj;
      if (this.descricao == null) {
         if (other.descricao != null) {
            return false;
         }
      } else if (!this.descricao.equals(other.descricao)) {
         return false;
      }
      return true;
   }

}
