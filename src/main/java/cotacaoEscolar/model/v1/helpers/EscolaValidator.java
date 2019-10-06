package cotacaoEscolar.model.v1.helpers;

import cotacaoEscolar.app.exceptions.IllegalError;

public interface EscolaValidator {

   public void validate();

   public static EscolaValidator create(final String nome) {
      return () -> {
         if ((nome == null) || nome.trim().isEmpty()) {
            throw new IllegalError("Uma escola não pode ser criada sem nome.");
         }

         if (nome.trim().length() < 4) {
            throw new IllegalError("Ahhh qual é uma escola tem mais de 4 letras.");
         }

         if (nome.length() > 100) {
            throw new IllegalError("Acho uma melhor ideia dar uma abreviada no nome dessa escola.");
         }

      };
   }
}
