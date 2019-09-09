 

public class ListaCotacao {
   // variáveis de instância - substitua o exemplo abaixo pelo seu próprio
   private final ListaProduto produtosEncontrados;
   private final ListaProduto produtosNaoEncontrados;
   private final Estabelecimento estabelecimento;

   /**
    * COnstrutor para objetos da classe ListaCotacao
    */
   public ListaCotacao() {
      // inicializa variáveis de instância
      this.produtosEncontrados = new ListaProduto();
      this.produtosNaoEncontrados = new ListaProduto();
      this.estabelecimento = new Estabelecimento();
   }

}