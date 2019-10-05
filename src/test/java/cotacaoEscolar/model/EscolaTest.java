package cotacaoEscolar.model;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import cotacaoEscolar.model.v1.EscolaReal;

public class EscolaTest {

   @Test
   public void testEqualsEscolaComNulo() {
      final Escola escola1 = EscolaReal.create("Escola1");
      final Escola escola2 = null;
      assertFalse(escola1.equals(escola2));
   }

}
