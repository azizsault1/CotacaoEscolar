 

/**
 * Escreva a descrição da classe ListaMaterial aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class ListaMaterial {
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
   private final Escola escola;
   private final int serie;
   private final ListaItem itens;

   /**
    * COnstrutor para objetos da classe ListaMaterial
    */
   public ListaMaterial(final Escola escola) {
      // inicializa variáveis de instância
      this.escola = escola;
      this.serie = 0;
      this.itens = new ListaItem();
   }

}
