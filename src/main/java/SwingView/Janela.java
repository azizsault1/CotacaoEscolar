package SwingView;

import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CotacaoEscolar.escola.model.Escola;
import CotacaoEscolar.item.model.DescricaoMaterialEscolar;
import CotacaoEscolar.item.model.Item;
import CotacaoEscolar.item.model.ListaItem;
import CotacaoEscolar.materialEscolar.modelo.ListaMaterial;
import SwingView.interfaces.Label;
import SwingView.interfaces.LabelFieldConfiguration;
import SwingView.interfaces.Posicao;
import servicos.ListaServicos;

public class Janela extends JFrame {
   private static final long serialVersionUID = 1L;
   private static final int ALTURA_TELA = Dimensoes.AlturaTela.getValor();
   private static final int LARGURA_TELA = Dimensoes.LarguraTela.getValor();
   private static final int ALTURA_COMPONENTES_PADRAO = Dimensoes.AlturaComponentPadrao.getValor();
   private static final int LARGURA_COMPONENTE_PADRAO = Dimensoes.LarguraComponentPadrao.getValor();
   private static final int MEIO_TELA = (Dimensoes.LarguraTela.getValor() / 2) - (Dimensoes.LarguraComponentPadrao.getValor() / 2);
   private static final int LINHA = 15;

   private static final int Y_INICIAL = 20;

   private final ListaServicos servicos;
   private Escola escolaEscolhida;

   public Janela(final ListaServicos listaServicos) {
      this.servicos = listaServicos;
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
      final Container contentPane = this.getContentPane();

      final JLabel cotacaoProdutosTitulo = new JLabel("Cotação de Protutos");
      cotacaoProdutosTitulo.setBounds(MEIO_TELA, Y_INICIAL, LARGURA_COMPONENTE_PADRAO, ALTURA_COMPONENTES_PADRAO);
      this.getContentPane().add(cotacaoProdutosTitulo);

      final int linha1 = this.calculaProximaLinha(Y_INICIAL);

      final List<Escola> escolas = this.servicos.getServicoEscola().todas();

      final LabelField<Escola> escola = this.criarEscola(linha1);
      final LabelField<Integer> series = this.criarSeries(linha1);
      final int linha2 = this.calculaProximaLinha(linha1);
      final LabelField<DescricaoMaterialEscolar> itens = this.criarItens(linha2);
      final int linha3 = this.calculaProximaLinha(linha2);

      final DefaultTableModel modelo = new DefaultTableModel() {
         private static final long serialVersionUID = 1L;

         @Override
         public boolean isCellEditable(final int row, final int column) {
            return false;
         }
      };
      final JTable tabela = this.criarTabela(modelo);
      final JScrollPane barraRolagem = new JScrollPane(tabela);
      barraRolagem.setBounds(Dimensoes.MarginColuna1.getValor(), linha3, 575, 350);

      final ItemListener listenerEscola = e -> {
         if (ItemEvent.SELECTED == e.getStateChange()) {
            this.escolaEscolhida = (Escola) e.getItem();
            final List<ListaMaterial> listaMateriais = this.servicos.getServicoListaMaterial().selecionePor(this.escolaEscolhida);
            final List<Integer> listaSeries = listaMateriais.stream().map(ListaMaterial::getSerie).collect(Collectors.toList());
            series.atualizarLista(listaSeries);
         }
      };

      final ItemListener listenerSeries = e -> {
         if (ItemEvent.SELECTED == e.getStateChange()) {
            modelo.setNumRows(0);
            final Integer serieEscolhida = (Integer) e.getItem();
            final ListaItem itensSelecionados = this.servicos.getServicoListaMaterial().selecionePor(this.escolaEscolhida, serieEscolhida);

            for (final Item item : itensSelecionados) {
               modelo.addRow(new Object[] { item, item.getQuantidade() });
            }
         }
      };

      escola.addListeners(listenerEscola);
      series.addListeners(listenerSeries);
      escola.atualizarLista(escolas);

      this.getContentPane().add(escola);
      this.getContentPane().add(series);
      this.getContentPane().add(itens);
      this.getContentPane().add(barraRolagem);

      this.setContentPane(contentPane);
   }

   private LabelField<Escola> criarEscola(final int linha1) {
      final Posicao posicao = Posicao.Factory.create(Dimensoes.MarginColuna1.getValor(), linha1);
      final Label label = Label.Factory.create(70, "Colégio:");

      final LabelFieldConfiguration configuration = LabelFieldConfiguration.Factory.create(posicao, label);
      final LabelField<Escola> colegio = new LabelField<>(configuration);
      return colegio;
   }

   private LabelField<Integer> criarSeries(final int linha1) {
      // Series
      final Posicao posicao = Posicao.Factory.create(Dimensoes.MarginColuna2.getValor(), linha1);
      final Label label = Label.Factory.create(50, "Series:");
      final LabelFieldConfiguration configuration = LabelFieldConfiguration.Factory.create(posicao, label);
      final LabelField<Integer> series = new LabelField<>(configuration);
      return series;
   }

   private LabelField<DescricaoMaterialEscolar> criarItens(final int linha2) {
      // Series
      final int largura = 575;
      final Posicao posicao = Posicao.Factory.create(Dimensoes.MarginColuna1.getValor(), linha2, largura);
      final Label label = Label.Factory.create(50, "Itens:");
      final LabelFieldConfiguration configuration = LabelFieldConfiguration.Factory.create(posicao, label);
      final LabelField<DescricaoMaterialEscolar> itens = new LabelField<>(configuration);
      itens.atualizarLista(this.servicos.getServicoItem().todasDescricoes());
      return itens;
   }

   private JTable criarTabela(final DefaultTableModel modelo) {
      modelo.addColumn("Item");
      modelo.addColumn("Quantidade");
      final JTable tabela = new JTable(modelo);
      tabela.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(final MouseEvent e) {
            final int row = tabela.rowAtPoint(e.getPoint());
            final int col = 0;
            JOptionPane.showInputDialog(null, "Value in final the cell clicked :" + tabela.getValueAt(row, col).toString());
         }
      });
      return tabela;
   }

   public static void main(final String[] args) {
      final ListaServicos servicos = new ListaServicos();
      new Janela(servicos);
   }
}