package cotacaoEscolar.model.v1;

public interface Manjada<Model> {

   public void salvar(final Model model);

   public boolean souNova();

   class ModelNovo<Model> implements Manjada<Model> {
      private static final String MENSAGEM = "Opsss você deveria ter perguntando antes se eu sou uma [%s] nova (isNova()) se eu for, pede para [%s](s).inserir(escola), final aí sim depois final você pode me atuzalizar.";

      @Override
      public boolean souNova() {
         return true;
      }

      @Override
      public void salvar(final Model model) {
         final String nomeModelo = model.getClass().getName();
         throw new UnsupportedOperationException(String.format(MENSAGEM, nomeModelo, nomeModelo));

      }

   }

}
