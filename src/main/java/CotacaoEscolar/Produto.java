package cotacaoEscolar;

import cotacaoEscolar.item.model.DescricaoMaterialEscolar;

/**
 * Escreva a descrição da classe Produto aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class Produto {
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
   private final DescricaoMaterialEscolar materialEscolar;

   /**
    * COnstrutor para objetos da classe Produto
    */
   public Produto() {
      // inicializa variáveis de instância
      this.materialEscolar = new DescricaoMaterialEscolar("Teste");
   }

}
