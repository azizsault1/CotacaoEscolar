package swingView.interfaces;

public interface Label {
   public int labelWidth();

   public String labelText();

   public final class Factory {
      public static Label create(final int width, final String label) {
         return new Label() {

            @Override
            public int labelWidth() {
               return width;
            }

            @Override
            public String labelText() {
               return label;
            }
         };
      }
   }
}
