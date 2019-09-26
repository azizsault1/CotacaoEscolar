package swingView.interfaces;

import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Item;
import swingView.Dimensoes;

public class LinhaItens extends JPanel {

   private static final long serialVersionUID = 1L;

   public interface EventoItemSelecionado {
      public void adicioneMaisUmItem(Item de);

   }

   private final JComboBox<DescricaoMaterialEscolar> combo;
   private final Collection<DescricaoMaterialEscolar> descricoes;

   public LinhaItens(final int linha, final Collection<DescricaoMaterialEscolar> descricoes, final EventoItemSelecionado itemSelecionado) {
      this.descricoes = descricoes;
      this.combo = new JComboBox<>();
      this.setLayout(null);
      this.addComponents(itemSelecionado);
      this.setBounds(0, linha, Dimensoes.LarguraTela.getValor(), Dimensoes.AlturaComponentPadrao.getValor());
   }

   private void addComponents(final EventoItemSelecionado itemSelecionado) {
      final JLabel label = new JLabel("Itens:");
      final int larguraLabel = 50;

      label.setBounds(Dimensoes.MarginColuna1.getValor(), 0, larguraLabel, Dimensoes.AlturaComponentPadrao.getValor());

      final int maginMaisLabelMaisEspaco = Dimensoes.MarginColuna1.getValor() + larguraLabel + Dimensoes.Espaco.getValor();
      final int larguraCombo = 300;

      this.combo.setBounds(maginMaisLabelMaisEspaco, 0, larguraCombo, Dimensoes.AlturaComponentPadrao.getValor());
      this.atualizarDescricoes(this.descricoes);

      final JButton adicionarItem = new JButton("Adicionar Item");
      final int marginMaisLabelMaisEspacoMaisComboMaisEspaco = maginMaisLabelMaisEspaco + larguraCombo + Dimensoes.Espaco.getValor();
      final int larguraAdicionar = (Dimensoes.LarguraLinha.getValor() - marginMaisLabelMaisEspacoMaisComboMaisEspaco);
      adicionarItem.setBounds(marginMaisLabelMaisEspacoMaisComboMaisEspaco, 0, larguraAdicionar, Dimensoes.AlturaComponentPadrao.getValor());
      adicionarItem.addActionListener(evento -> {
         final DescricaoMaterialEscolar material = (DescricaoMaterialEscolar) this.combo.getSelectedItem();
         final Item de = new Item(material, 1);
         itemSelecionado.adicioneMaisUmItem(de);
      });

      this.add(label);
      this.add(this.combo);
      this.add(adicionarItem);

   }

   private void atualizarDescricoes(final Collection<DescricaoMaterialEscolar> novasDescricoes) {
      this.combo.removeAllItems();
      novasDescricoes.forEach(this.combo::addItem);
      if (!novasDescricoes.isEmpty()) {
         this.combo.setSelectedItem(novasDescricoes.stream().findFirst());
      }
   }

}
