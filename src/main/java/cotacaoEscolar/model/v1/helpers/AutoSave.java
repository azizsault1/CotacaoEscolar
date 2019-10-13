package cotacaoEscolar.model.v1.helpers;

import cotacaoEscolar.app.exceptions.FoiNao;

public interface AutoSave<Model> {

   public Model save() throws FoiNao;

   public boolean canIPersist();

   class ModelNovo<Model> implements AutoSave<Model> {
      private static final String MENSAGEM = "Opsss você deveria ter perguntando antes se eu sou uma [%s] nova (isNova()) se eu for, pede para [%s](s).inserir(escola), final aí sim depois final você pode me atuzalizar.";

      @Override
      public boolean canIPersist() {
         return true;
      }

      @Override
      public Model save() throws FoiNao {
         final String modelName = this.getClass().getName();
         throw new UnsupportedOperationException(String.format(MENSAGEM, modelName, modelName));
      }
   }

}