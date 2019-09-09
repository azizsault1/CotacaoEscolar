package SwingView;

public enum Dimensoes {
   AlturaComponentPadrao(30),
   LarguraComponentPadrao(200),
   LarguraTela(800),
   AlturaTela(600),
   MarginColuna1(80),
   MarginColuna2(400);

   private int valor;

   private Dimensoes(final int valor) {
      this.valor = valor;
   }

   public int getValor() {
      return this.valor;
   }

}
