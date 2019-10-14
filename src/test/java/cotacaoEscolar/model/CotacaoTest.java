package cotacaoEscolar.model;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class CotacaoTest {

   @Test
   public void DadoMaisItensDoQueAQuantidadeEmEstoqueDeveAdicionarObservacao() {
      final Cotacao cotacao = new Cotacao("Desc1", 15, 10, BigDecimal.valueOf(30));
      final String obtido = cotacao.toReport();
      //@formatter:off
      final String esperado = "Item: Desc1\n" +
            "Quantidade: 15    Total unitário: R$ 30,00\n" +
            "Valor da cotação: R$ 450,00\n" +
            "Observação:\n" +
            "  Infelizmente não possuímos a quantidade desejada:15, apenas possuímos:10 para critério de cotação, o valor total considera como se tivéssmos todos os produtos.\n" +
            "---\n" +
            "";
      //@formatter:on
      Assert.assertEquals(esperado, obtido);
   }

   @Test
   public void DadoMenosItensDoQueAQuantidadeEmEstoqueNaoSeDeveAdicionarObservacao() {
      final Cotacao cotacao = new Cotacao("Desc1", 5, 10, BigDecimal.valueOf(20));
      final String obtido = cotacao.toReport();
      //@formatter:off
      final String esperado = "Item: Desc1\n"
                             + "Quantidade: 5    Total unitário: R$ 20,00\n"
                             + "Valor da cotação: R$ 100,00\n"
                             + "---\n";
      //@formatter:on
      Assert.assertEquals(esperado, obtido);
   }

}
