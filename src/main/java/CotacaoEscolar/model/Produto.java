package cotacaoEscolar.model;

import java.math.BigDecimal;

/**
 * Escreva a descrição da classe Produto aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class Produto {
   private final DescricaoMaterialEscolar materialEscolar;
   private final BigDecimal valor;

   /**
    * COnstrutor para objetos da classe Produto
    */
   public Produto(final String descricao, final BigDecimal valor) {
      this.materialEscolar = new DescricaoMaterialEscolar(descricao);
      this.valor = valor;
   }

}
