package swingView.interfaces;

public interface LabelFieldConfiguration extends Posicao, Label {

   public static final class Factory {

      private Factory() {
      }

      public static final LabelFieldConfiguration create(final Posicao posicao, final Label label) {
         return new LabelFieldConfiguration() {

            @Override
            public int x() {
               return posicao.x();
            }

            @Override
            public int y() {
               return posicao.y();
            }

            @Override
            public int labelWidth() {
               return label.labelWidth();
            }

            @Override
            public String labelText() {
               return label.labelText();
            }

         };
      }
   }

}
