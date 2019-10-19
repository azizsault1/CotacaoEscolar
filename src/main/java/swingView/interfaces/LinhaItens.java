package swingView.interfaces;

import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolarImpl;
import cotacaoEscolar.model.v1.ItemImpl;
import swingView.Dimensoes;

public class LinhaItens extends JPanel {

   private static final long serialVersionUID = 1L;
   private static final int LARGURA_LABEL = 50;
   private static final int LARGURA_DO_COMBO = 300;

   public interface EventoItemSelecionado {
      public void adicioneMaisUmItem(Item de);

   }

   private final JComboBox<DescricaoMaterialEscolarImpl> combo;
   private final Collection<DescricaoMaterialEscolarImpl> descricoes;

   public LinhaItens(final int linha, final Collection<DescricaoMaterialEscolarImpl> descricoes, final EventoItemSelecionado itemSelecionado) {
      this.descricoes = descricoes;
      this.combo = new JComboBox<>();
      this.setLayout(null);
      this.addComponents(itemSelecionado);
      this.setBounds(0, linha, Dimensoes.LarguraTela.getValor(), Dimensoes.AlturaComponentPadrao.getValor());
   }

   private void addComponents(final EventoItemSelecionado itemSelecionado) {
      final JLabel label = new JLabel("Itens:");

      label.setBounds(Dimensoes.MarginColuna1.getValor(), 0, LARGURA_LABEL, Dimensoes.AlturaComponentPadrao.getValor());

      final int maginMaisLabelMaisEspaco = Dimensoes.MarginColuna1.getValor() + LARGURA_LABEL + Dimensoes.Espaco.getValor();

      this.combo.setBounds(maginMaisLabelMaisEspaco, 0, LARGURA_DO_COMBO, Dimensoes.AlturaComponentPadrao.getValor());
      this.atualizarDescricoes(this.descricoes);

      final JButton adicionarItem = new JButton("Adicionar Item");
      final int marginMaisLabelMaisEspacoMaisComboMaisEspaco = maginMaisLabelMaisEspaco + LARGURA_DO_COMBO + Dimensoes.Espaco.getValor();
      final int larguraAdicionar = (Dimensoes.LarguraLinha.getValor() - marginMaisLabelMaisEspacoMaisComboMaisEspaco);
      adicionarItem.setBounds(marginMaisLabelMaisEspacoMaisComboMaisEspaco, 0, larguraAdicionar, Dimensoes.AlturaComponentPadrao.getValor());
      adicionarItem.addActionListener(evento -> {
         final DescricaoMaterialEscolarImpl material = (DescricaoMaterialEscolarImpl) this.combo.getSelectedItem();
         if (material != null) {
            final ItemImpl de = new ItemImpl(material, 1);
            itemSelecionado.adicioneMaisUmItem(de);
         }
      });

      this.add(label);
      this.add(this.combo);
      this.add(adicionarItem);

   }

   private void atualizarDescricoes(final Collection<DescricaoMaterialEscolarImpl> novasDescricoes) {
      this.combo.removeAllItems();
      novasDescricoes.forEach(this.combo::addItem);
      if (!novasDescricoes.isEmpty()) {
         this.combo.setSelectedItem(novasDescricoes.stream().findFirst());
      }
   }

}
