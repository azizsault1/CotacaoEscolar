 

/**
 * Escreva a descrição da classe ResultadoCotacao aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class ResultadoCotacao {
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
   private final ListaMaterial materiais;
   private final ListaCotacao listaCotacao;

   /**
    * COnstrutor para objetos da classe ResultadoCotacao
    */
   public ResultadoCotacao(ListaMaterial material) {
      // inicializa variáveis de instância
      this.materiais = material;
      this.listaCotacao = new ListaCotacao();
   }

}
