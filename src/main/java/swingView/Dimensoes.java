package swingView;

public enum Dimensoes {
   AlturaComponentPadrao(30),
   LarguraLabel(200),
   LarguraLinha(660),
   LarguraTela(800),
   AlturaTela(600),
   MarginColuna1(80),
   Espaco(5),
   MarginColuna2(400);

   private int valor;

   private Dimensoes(final int valor) {
      this.valor = valor;
   }

   public int getValor() {
      return this.valor;
   }

}
