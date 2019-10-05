package cotacaoEscolar.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import cotacaoEscolar.model.v1.EscolaReal;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.service.ServicoDescricaoMaterialEscolar;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoEstabelecimento;
import cotacaoEscolar.service.ServicoListaMaterial;

public class ControllerBuscaRestTest {

   private final ServicoEscola servicoEscola;
   private final ServicoDescricaoMaterialEscolar servicoDescricaoMaterialEscolar;
   private final ServicoListaMaterial servicoListaMaterial;
   private final ControllerBuscaRest controllerBuscaRest;
   private final ServicoEstabelecimento servicoEstabelecimento;

   public ControllerBuscaRestTest() {
      this.servicoEscola = mock(ServicoEscola.class);
      this.servicoDescricaoMaterialEscolar = mock(ServicoDescricaoMaterialEscolar.class);
      this.servicoListaMaterial = mock(ServicoListaMaterial.class);
      this.servicoEstabelecimento = mock(ServicoEstabelecimento.class);

      this.controllerBuscaRest = new ControllerBuscaRest(this.servicoEscola, this.servicoDescricaoMaterialEscolar, this.servicoListaMaterial,
            this.servicoEstabelecimento);
   }

   @Test
   public void quandoBuscoAListaDeSeriesDeUmaEscolaVouRetornarAListaDeSeries() {
      final String nomeEscola = "Escola1";
      final EscolaReal escola = mock(EscolaReal.class);
      final Serie serie = mock(Serie.class);
      final Collection<Serie> series = Arrays.asList(serie, serie, serie, serie);

      when(this.servicoEscola.buscar(nomeEscola)).thenReturn(Optional.of(escola));
      when(this.servicoListaMaterial.selecioneSeriesPor(escola)).thenReturn(series);
      //@formatter:off
      when(serie.getSerie())
         .thenReturn("Serie1")
         .thenReturn("Serie2")
         .thenReturn("Serie3")
         .thenReturn("Serie4");
      //formatter:off

      final Collection<String> seriesDa = this.controllerBuscaRest.SeriesDa(nomeEscola);
      final List<String> resultado = new ArrayList<>(seriesDa);
      assertEquals("Serie1", resultado.get(0));
      assertEquals("Serie2", resultado.get(1));
      assertEquals("Serie3", resultado.get(2));
      assertEquals("Serie4", resultado.get(3));
   }

}
