package cotacaoEscolar.model.v1;

import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Representa o resultado de várias cotações em diversos estabelecimentos diferentes.")
public class ResultadoCotacao {

   private final List<ResultadoCotacaoEstabelecimentoImpl> resultado;

   public ResultadoCotacao(final List<ResultadoCotacaoEstabelecimentoImpl> resultado) {
      this.resultado = resultado;
   }

   public List<ResultadoCotacaoEstabelecimentoImpl> getResultado() {
      return this.resultado;
   }

   @Override
   public String toString() {
      return "ResultadoCotacao [resultado=" + this.resultado + "]";
   }

   public String toReport() {
      final StringBuffer report = new StringBuffer();
      this.resultado.forEach(resultado -> report.append(resultado.toReport()));
      report.append("OBRIGADO POR COTAR COM A GENTE!!!");
      return report.toString();
   }

}
