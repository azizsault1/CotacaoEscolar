package cotacaoEscolar.model;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import cotacaoEscolar.model.v1.EscolaReal;
import cotacaoEscolar.repository.Repository;

public class EscolaTest {

   @Test
   public void testEqualsEscolaComNulo() {

      final Repository<Escola> repository = mock(Repository.class);
      final Escola escola1 = EscolaReal.create(repository, "Escola1");
      final Escola escola2 = null;
      assertFalse(escola1.equals(escola2));
   }

}
