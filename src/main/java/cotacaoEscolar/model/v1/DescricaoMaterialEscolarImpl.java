package cotacaoEscolar.model.v1;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.DescricaoMaterialEscolar;
import io.swagger.annotations.ApiModel;

@JsonSerialize
@ApiModel(description = "A descrição de um material escolar é a descrição ou de um um Produto ou de um Item. Exemplo: \"Lapis de cor.\"")
public class DescricaoMaterialEscolarImpl implements Comparable<DescricaoMaterialEscolar>, DescricaoMaterialEscolar {
   private final String descricao;

   private DescricaoMaterialEscolarImpl(final String descricao) {
      this.descricao = descricao;
   }

   @Override
   public String getDescricao() {
      return this.descricao;
   }

   @Override
   public String toString() {
      return this.descricao;
   }

   @Override
   public int compareTo(final DescricaoMaterialEscolar o) {
      return this.descricao.compareTo(o.getDescricao());
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
      final DescricaoMaterialEscolarImpl other = (DescricaoMaterialEscolarImpl) obj;
      if (this.descricao == null) {
         if (other.descricao != null) {
            return false;
         }
      } else if (!this.descricao.equals(other.descricao)) {
         return false;
      }
      return true;
   }

   @JsonCreator
   public static DescricaoMaterialEscolarImpl create(@JsonProperty("descricao") final String descricao) {
      if ((descricao == null) || descricao.isEmpty()) {
         throw new IllegalError("A descrição do material escolar não pode ser em branco.");
      }
      return new DescricaoMaterialEscolarImpl(descricao);
   }

   public static List<DescricaoMaterialEscolarImpl> create(final int quantidade) {
      final List<DescricaoMaterialEscolarImpl> result = new ArrayList<>();
      for (int i = 0; i < quantidade; i++) {
         result.add(DescricaoMaterialEscolarImpl.create("Material" + i));
      }
      return result;
   }

}
