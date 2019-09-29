package swingView.interfaces;

public interface Posicao {
   public int x();

   public int y();

   public final class Factory {
      public static Posicao create(final int x, final int y) {
         return new Posicao() {
            @Override
            public int x() {
               return x;
            }

            @Override
            public int y() {
               return y;
            }

         };
      }
   }
}
