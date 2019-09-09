package SwingView;

import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SwingView.interfaces.LabelFieldConfiguration;

public class LabelField<E> extends JPanel {
   private static final long serialVersionUID = 1L;
   private static final int ESPACO = 5;
   private static final int ALTURA_COMPONENTES_PADRAO = Dimensoes.AlturaComponentPadrao.getValor();
   private static final int LARGURA_COMPONENTE_PADRAO = Dimensoes.LarguraComponentPadrao.getValor();

   private final LabelFieldConfiguration conf;
   private final JComboBox<E> combo;

   public LabelField(final LabelFieldConfiguration conf) {
      this.conf = conf;
      this.combo = new JComboBox<>();
      this.setLayout(null);
      this.addComponents();
   }

   private void addComponents() {
      final JLabel label = new JLabel(this.conf.labelText());
      label.setBounds(0, 0, this.conf.labelWidth(), ALTURA_COMPONENTES_PADRAO);
      this.add(label);

      final int larguraComponente = this.conf.largura() > 0 ? this.conf.largura() : label.getWidth() + 5 + LARGURA_COMPONENTE_PADRAO;
      final int larguraCombo = larguraComponente - (ESPACO + this.conf.labelWidth());

      this.combo.setBounds(this.conf.labelWidth() + ESPACO, 0, larguraCombo, ALTURA_COMPONENTES_PADRAO);
      this.add(this.combo);

      this.setBounds(this.conf.x(), this.conf.y(), larguraComponente, ALTURA_COMPONENTES_PADRAO);
   }

   public void addListeners(final ItemListener itemListener) {
      this.combo.addItemListener(itemListener);
   }

   public void atualizarLista(final List<E> novaLista) {
      this.combo.removeAllItems();
      novaLista.forEach(this.combo::addItem);
      if (!novaLista.isEmpty()) {
         this.combo.setSelectedItem(novaLista.get(0));
      }
   }

}
