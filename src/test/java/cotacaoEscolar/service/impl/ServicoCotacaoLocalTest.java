package cotacaoEscolar.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import cotacaoEscolar.model.ResultadoCotacao;
import cotacaoEscolar.model.listas.ListaEstabelecimento;
import cotacaoEscolar.model.listas.ListaMaterial;

public class ServicoCotacaoLocalTest {

   private final ServicoCotacaoLocal servico;

   private ServicoCotacaoLocalTest() {
      this.servico = new ServicoCotacaoLocal();
   }

   @Test
   public void testCotar() {
      final ListaMaterial lista = Mockito.mock(ListaMaterial.class);
      final ListaEstabelecimento estabelecimentos = Mockito.mock(ListaEstabelecimento.class);

      final ResultadoCotacao resultado = this.servico.cotar(lista, estabelecimentos);

      Assert.assertNotNull(resultado);

   }

}
