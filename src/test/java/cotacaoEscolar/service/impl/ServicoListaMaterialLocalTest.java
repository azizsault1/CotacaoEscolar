package cotacaoEscolar.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import cotacaoEscolar.app.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.EscolaReal;
import cotacaoEscolar.model.v1.Item;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.repository.ListaMaterialRepository;

public class ServicoListaMaterialLocalTest {

   private final ServicoListaMaterialLocal servico;
   private final ListaMaterialRepository repository;

   @Rule
   public ExpectedException expectedException = ExpectedException.none();

   @After
   public void tearDown() {
      Mockito.validateMockitoUsage();
   }

   public ServicoListaMaterialLocalTest() {
      this.repository = Mockito.mock(ListaMaterialRepository.class);
      this.servico = new ServicoListaMaterialLocal(this.repository);
   }

   @Test
   public void dadoUmaEscolaInvalidaEOBancoRetornarNuloRetornaErroDeEscola() {
      this.expectedException.expect(IllegalError.class);
      this.expectedException.expectMessage("Opps... essa escola não existe.");

      final EscolaReal escola = null;
      final Serie serie = Serie.create("1a serie");
      final Collection<ListaMaterial> lista = null;

      Mockito.when(this.repository.materiais()).thenReturn(lista);

      this.servico.selecionePor(escola, serie);
   }

   @Test
   public void dadoEscolaEUmaSerieQueNaoExisteQuandoSelecionoMaterialRetornaErroDeSerie() {
      this.expectedException.expect(IllegalError.class);
      this.expectedException.expectMessage("Opps... essa serie não existe.");

      final EscolaReal escola = EscolaReal.create("Escola");
      final Serie serie = null;
      final ListaMaterial listaMaterial = Mockito.mock(ListaMaterial.class);
      final Collection<ListaMaterial> lista = Arrays.asList(listaMaterial);

      Mockito.when(this.repository.materiais()).thenReturn(lista);

      this.servico.selecionePor(escola, serie);
   }

   @Test
   public void dadoUmaEscolaEUmaSerieSeNaoEncontrarAEscolaSignificaQueTemQueCriarUmaListaNova() {
      final EscolaReal escola = Mockito.mock(EscolaReal.class);
      final Serie serie = Serie.create("1a serie");
      final ListaMaterial listaMaterial = Mockito.mock(ListaMaterial.class);
      final Collection<ListaMaterial> lista = Arrays.asList(listaMaterial);

      Mockito.when(this.repository.materiais()).thenReturn(lista);
      Mockito.when(listaMaterial.pertenceA(escola)).thenReturn(false);

      final ListaMaterial material = this.servico.selecionePor(escola, serie);
      assertNotNull(material);
      assertEquals(escola, material.getEscola());
      assertEquals(serie, material.getSerie());
      assertTrue(material.getItens().isEmpty());
   }

   @Test
   public void dadoUmaEscolaEUmaSerieSeEncontrarASerieSignificaQueTemQueCriarUmaListaNova() {
      final EscolaReal escola = Mockito.mock(EscolaReal.class);
      final Serie serie = Serie.create("1a serie");
      final ListaMaterial listaMaterial = Mockito.mock(ListaMaterial.class);
      final Collection<ListaMaterial> lista = Arrays.asList(listaMaterial);

      Mockito.when(this.repository.materiais()).thenReturn(lista);
      Mockito.when(listaMaterial.pertenceA(escola)).thenReturn(true);
      Mockito.when(listaMaterial.pertenceASerie(serie)).thenReturn(false);

      final ListaMaterial material = this.servico.selecionePor(escola, serie);
      assertNotNull(material);
      assertEquals(escola, material.getEscola());
      assertEquals(serie, material.getSerie());
      assertTrue(material.getItens().isEmpty());
   }

   @Test
   public void dadoUmaEscolaEUmaSerieSeEncontrarRetornaAListaEncontrada() {
      final EscolaReal escola = Mockito.mock(EscolaReal.class);
      final Serie serie = Serie.create("1a serie");
      final Item item = Mockito.mock(Item.class);
      final List<Item> itens = Arrays.asList(item);
      final ListaMaterial listaMaterial = Mockito.mock(ListaMaterial.class);
      final Collection<ListaMaterial> lista = Arrays.asList(listaMaterial);

      Mockito.when(this.repository.materiais()).thenReturn(lista);
      Mockito.when(listaMaterial.pertenceA(escola)).thenReturn(true);
      Mockito.when(listaMaterial.pertenceASerie(serie)).thenReturn(true);
      Mockito.when(listaMaterial.getEscola()).thenReturn(escola);
      Mockito.when(listaMaterial.getSerie()).thenReturn(serie);
      Mockito.when(listaMaterial.getItens()).thenReturn(itens);

      final ListaMaterial material = this.servico.selecionePor(escola, serie);
      assertNotNull(material);
      assertEquals(escola, material.getEscola());
      assertEquals(serie, material.getSerie());
      assertFalse(material.getItens().isEmpty());
   }

   @Test
   public void dadoUmaEscolaEOBancoRetornaNuloOSistemaNaoDeveApresentarErroERetornarUmaListaVazia() {
      final Escola escola = Mockito.mock(EscolaReal.class);

      Mockito.when(this.repository.materiais()).thenReturn(null);

      final Collection<ListaMaterial> material = this.servico.selecionePor(escola);
      assertNotNull(material);
      assertTrue(material.isEmpty());
   }

   @Test
   public void dadoUmaEscolaSemMaterialEscolarAoSelecionarPorEscolaVaiRetornarUmaListaVazia() {
      final Escola escola = Mockito.mock(EscolaReal.class);
      final ListaMaterial listaMaterial = Mockito.mock(ListaMaterial.class);
      final Collection<ListaMaterial> lista = Arrays.asList(listaMaterial);

      Mockito.when(this.repository.materiais()).thenReturn(lista);
      Mockito.when(listaMaterial.pertenceA(escola)).thenReturn(false);

      final Collection<ListaMaterial> material = this.servico.selecionePor(escola);
      assertNotNull(material);
      assertTrue(material.isEmpty());
   }

   @Test
   public void dadoUmaEscolaComMaterialEscolarAoSelecionarPorEscolaVaiRetornarALista() {
      final Escola escola = Mockito.mock(EscolaReal.class);
      final ListaMaterial listaMaterial = Mockito.mock(ListaMaterial.class);
      final Collection<ListaMaterial> lista = Arrays.asList(listaMaterial);

      Mockito.when(this.repository.materiais()).thenReturn(lista);
      Mockito.when(listaMaterial.pertenceA(escola)).thenReturn(true);

      final Collection<ListaMaterial> material = this.servico.selecionePor(escola);
      assertNotNull(material);
      assertFalse(material.isEmpty());
   }

   @Test
   public void dadoUmaEscolaSerieEItemQuandoRemoverVaiRemoverOItemDoMaterial() {
      final EscolaReal escola = Mockito.mock(EscolaReal.class);
      final Serie serie = Serie.create("1a serie");
      final Item itemARemover = Mockito.mock(Item.class);
      final List<Item> itens = new ArrayList<>(Arrays.asList(itemARemover));
      final ListaMaterial listaMaterial = Mockito.mock(ListaMaterial.class);
      final Collection<ListaMaterial> lista = Arrays.asList(listaMaterial);

      Mockito.when(this.repository.materiais()).thenReturn(lista);
      Mockito.when(listaMaterial.pertenceA(escola)).thenReturn(true);
      Mockito.when(listaMaterial.pertenceASerie(serie)).thenReturn(true);
      Mockito.when(listaMaterial.getItens()).thenReturn(itens);

      this.servico.remover(escola, serie, itemARemover);
      assertTrue(itens.isEmpty());
      Mockito.verify(this.repository, Mockito.never()).salvaSaPorra(Mockito.any(ListaMaterial.class));
   }

   @Test
   public void dadoUmaEscolaSerieEItemQuandoAdicionarVaiOItemNoMaterial() {
      final EscolaReal escola = Mockito.mock(EscolaReal.class);
      final Serie serie = Serie.create("1a serie");
      final Item itemAAdicionar = Mockito.mock(Item.class);
      final List<Item> itens = new ArrayList<>();
      final ListaMaterial listaMaterial = Mockito.mock(ListaMaterial.class);
      final Collection<ListaMaterial> lista = Arrays.asList(listaMaterial);

      Mockito.when(this.repository.materiais()).thenReturn(lista);
      Mockito.when(listaMaterial.pertenceA(escola)).thenReturn(true);
      Mockito.when(listaMaterial.pertenceASerie(serie)).thenReturn(true);
      Mockito.when(listaMaterial.getItens()).thenReturn(itens);

      this.servico.adicionar(escola, serie, itemAAdicionar);
      assertFalse(itens.isEmpty());
      Mockito.verify(this.repository, Mockito.never()).salvaSaPorra(Mockito.any(ListaMaterial.class));
   }

   @Test
   public void DadoUmaEscolaAoBuscarAsSeriesVaiRetornarAListaDeSeries() {
      final Escola escola = Mockito.mock(EscolaReal.class);
      final Serie serie1 = Serie.create("Serie1");
      final Serie serie2 = Serie.create("Serie2");
      final Serie serie3 = Serie.create("Serie3");
      final ListaMaterial listaMaterial = Mockito.mock(ListaMaterial.class);
      final Collection<ListaMaterial> lista = Arrays.asList(listaMaterial, listaMaterial, listaMaterial);

      Mockito.when(this.repository.materiais()).thenReturn(lista);
      Mockito.when(listaMaterial.pertenceA(escola)).thenReturn(true);
      Mockito.when(listaMaterial.getSerie()).thenReturn(serie1).thenReturn(serie2).thenReturn(serie3);

      final Collection<Serie> series = this.servico.selecioneSeriesPor(escola);
      assertFalse(series.isEmpty());
      final List<Serie> seriesEmLista = new ArrayList<>(series);
      assertEquals(serie1, seriesEmLista.get(0));
      assertEquals(serie2, seriesEmLista.get(1));
      assertEquals(serie3, seriesEmLista.get(2));
   }

}
