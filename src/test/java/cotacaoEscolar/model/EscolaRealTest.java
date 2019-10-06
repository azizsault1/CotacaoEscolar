package cotacaoEscolar.model;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.v1.EscolaReal;
import cotacaoEscolar.service.ServicoEscola;

public class EscolaRealTest {

   @Test
   public void testEqualsEscolaComNulo() {
      final ServicoEscola servico = mock(ServicoEscola.class);
      final Escola impl = new EscolaImpl();
      final Escola escola1 = EscolaReal.create(servico, impl);
      final Escola escola2 = null;
      assertFalse(escola1.equals(escola2));
   }

   class EscolaImpl implements Escola {

      @Override
      public int compareTo(final Escola o) {
         return -1;
      }

      @Override
      public Escola salvar() throws FoiNao {
         return null;
      }

      @Override
      public boolean souNova() {
         return true;
      }

      @Override
      public String getNome() {
         return "Escola1";
      }

   }

}
