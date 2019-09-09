package CotacaoEscolar.item.model;

/**
 * Escreva a descrição da classe Item aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class Item {
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
   private final DescricaoMaterialEscolar materialEscolar;
   private final int quantidade;

   /**
    * COnstrutor para objetos da classe Item
    */
   public Item(final DescricaoMaterialEscolar descricaoMaterialEscolar, final int quantidade) {
      this.materialEscolar = descricaoMaterialEscolar;
      this.quantidade = quantidade;
   }

   public DescricaoMaterialEscolar getDescricao() {
      return this.materialEscolar;
   }

   public int getQuantidade() {
      return this.quantidade;
   }

}
