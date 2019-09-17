package cotacaoEscolar.model;

import java.util.List;

public class ResultadoCotacao {

   private final List<ResultadoCotacaoEstabelecimento> resultado;

   public ResultadoCotacao(final List<ResultadoCotacaoEstabelecimento> resultado) {
      this.resultado = resultado;
   }

   public List<ResultadoCotacaoEstabelecimento> getResultado() {
      return this.resultado;
   }

   @Override
   public String toString() {
      return "ResultadoCotacao [resultado=" + this.resultado + "]";
   }

}
