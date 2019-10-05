package cotacaoEscolar.app.exceptions;

public class FoiNao extends Exception {

   private static final long serialVersionUID = 1L;

   public FoiNao(final String mensagem, final Exception e) {
      super(mensagem, e);
   }

}
