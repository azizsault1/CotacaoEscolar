package swingView;

import java.awt.Container;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import cotacaoEscolar.controller.ControllerCotacao;
import cotacaoEscolar.controller.ControllerMaterialEscolar;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.ResultadoCotacao;
import cotacaoEscolar.model.listas.ListaMaterial;
import swingView.LabelFieldEscola.EventoEscolaSelecionada;
import swingView.LabelFieldSeries.EventoSerieSelecionada;
import swingView.interfaces.LinhaItens;
import swingView.interfaces.LinhaItens.EventoItemSelecionado;

public class Janela extends JFrame implements EventoItemSelecionado, EventoEscolaSelecionada, EventoSerieSelecionada {
   private static final long serialVersionUID = 1L;
   private static final int ALTURA_TELA = Dimensoes.AlturaTela.getValor();
   private static final int LARGURA_TELA = Dimensoes.LarguraTela.getValor();
   private static final int ALTURA_COMPONENTES_PADRAO = Dimensoes.AlturaComponentPadrao.getValor();
   private static final int LARGURA_COMPONENTE_PADRAO = Dimensoes.LarguraLinha.getValor();
   private static final int MEIO_TELA = (Dimensoes.LarguraTela.getValor() / 2) - (Dimensoes.LarguraLabel.getValor() / 2);
   private static final int LINHA = 15;

   private static final int Y_INICIAL = 20;

   private final ControllerMaterialEscolar controller;
   private final ControllerCotacao controllerCotacao;

   private LabelFieldEscola escolas;
   private LabelFieldSeries series;
   private LinhaItens itens;
   private TabelaMaterialEscolar tabelaMaterial;

   public Janela(final ControllerMaterialEscolar controllerMateriais, final ControllerCotacao controllerCotacao) {
      this.controller = controllerMateriais;
      this.controllerCotacao = controllerCotacao;

      this.addComponents();
      this.configureWindow();
   }

   private void configureWindow() {
      this.setVisible(Boolean.TRUE);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setTitle("Cadastro de usuario");
      this.setSize(LARGURA_TELA, ALTURA_TELA);
      this.setResizable(Boolean.FALSE);
      this.setLocationRelativeTo(null);
   }

   private int calculaProximaLinha(final int yAtual) {
      return yAtual + ALTURA_COMPONENTES_PADRAO + LINHA;
   }

   private void addComponents() {
      this.setLayout(null);

      final JLabel cotacaoProdutosTitulo = new JLabel("Cotação de Protutos");
      cotacaoProdutosTitulo.setBounds(MEIO_TELA, Y_INICIAL, LARGURA_COMPONENTE_PADRAO, ALTURA_COMPONENTES_PADRAO);

      final int linha1 = this.calculaProximaLinha(Y_INICIAL);
      final int linha2 = this.calculaProximaLinha(linha1);
      final int linha3 = this.calculaProximaLinha(linha2);

      this.itens = new LinhaItens(linha2, this.controller.todasDescricoes(), this);

      final Collection<Escola> escolas = this.controller.todasEscolas();

      this.escolas = new LabelFieldEscola(linha1, this);
      this.series = new LabelFieldSeries(linha1, this);
      this.tabelaMaterial = new TabelaMaterialEscolar();
      this.atualizar(escolas);

      final JButton cotar = new JButton("Cotar");
      cotar.setBounds(Dimensoes.MarginColuna1.getValor(), 520, 575, 40);
      cotar.addActionListener(listener -> {
         final Optional<Escola> escola = this.escolas.getEscolaEscolhida();
         final Optional<Integer> serie = this.series.getSerieEscolhida();
         final List<Item> itensEscohidos = this.tabelaMaterial.getItens();

         final ListaMaterial material;
         if ((!escola.isPresent()) || !serie.isPresent()) {
            material = ListaMaterial.Factory.criarListaVazia();
         } else {
            material = new ListaMaterial(escola.get(), serie.get(), itensEscohidos);
         }
         final ResultadoCotacao resultado = this.controllerCotacao.cotar(material);
         Relatorio.displayGUI(resultado);
      });

      final JScrollPane barraRolagem = new JScrollPane(this.tabelaMaterial.getTable());
      barraRolagem.setBounds(Dimensoes.MarginColuna1.getValor(), linha3, 575, 350);

      final Container contentPane = this.getContentPane();
      contentPane.add(cotacaoProdutosTitulo);
      contentPane.add(this.escolas);
      contentPane.add(this.series);
      contentPane.add(this.itens);
      contentPane.add(barraRolagem);
      contentPane.add(cotar);
      this.setContentPane(contentPane);
   }

   private void atualizar(final Collection<Escola> escolas) {
      this.escolas.atualizar(escolas);
   }

   @Override
   public void escolaSelecionada(final Escola escola) {
      final Collection<ListaMaterial> listaMateriais = this.queroAListaDeMateriaisDaEscola(Optional.of(escola));
      final List<Integer> listaSeries = this.queroAsSeriesDaListaDeMateriais(listaMateriais);
      this.series.atualizarLista(listaSeries);

   }

   @Override
   public void serieSelecionada(final Integer serie) {
      final Optional<Escola> escolaEscolhida = this.escolas.getEscolaEscolhida();
      final Optional<Integer> seriesEscolhida = Optional.of(serie);
      final ListaMaterial material = this.queroAListaDeMaterial(escolaEscolhida, seriesEscolhida);
      this.tabelaMaterial.atualizar(material.getItens());

   }

   @Override
   public void adicioneMaisUmItem(final Item item) {
      this.tabelaMaterial.adicioneMaisUmItem(item);
   }

   private Collection<ListaMaterial> queroAListaDeMateriaisDaEscola(final Optional<Escola> escola) {
      if (escola.isPresent()) {
         return this.controller.selecioneMaterialPor(escola.get());
      } else {
         return Collections.emptyList();
      }
   }

   private List<Integer> queroAsSeriesDaListaDeMateriais(final Collection<ListaMaterial> listaMateriais) {
      final List<Integer> series = listaMateriais.stream().map(ListaMaterial::getSerie).collect(Collectors.toList());
      Collections.sort(series);
      return series;
   }

   private ListaMaterial queroAListaDeMaterial(final Optional<Escola> escola, final Optional<Integer> serie) {
      if (!escola.isPresent() || !serie.isPresent()) {
         return ListaMaterial.Factory.criarListaVazia();
      }

      return this.controller.selecioneMaterialPor(escola.get(), serie.get());
   }

}