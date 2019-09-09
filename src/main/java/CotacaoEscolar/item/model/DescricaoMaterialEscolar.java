package CotacaoEscolar.item.model;

/**
 * Escreva a descrição da classe DescricaoMaterialEscolar aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class DescricaoMaterialEscolar {
   private final String descricao;

   public DescricaoMaterialEscolar(final String descricao) {
      this.descricao = descricao;
   }

   @Override
   public String toString() {
      return this.descricao;
   }

}
