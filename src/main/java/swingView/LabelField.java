package swingView;

import java.awt.event.ItemListener;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import swingView.interfaces.LabelFieldConfiguration;

public class LabelField<E> extends JPanel {
   private static final long serialVersionUID = 1L;
   private static final int ESPACO = 5;
   private static final int ALTURA_COMPONENTES_PADRAO = Dimensoes.AlturaComponentPadrao.getValor();
   private static final int LARGURA_COMPONENTE_PADRAO = Dimensoes.LarguraLabel.getValor();

   private final LabelFieldConfiguration conf;
   protected final JComboBox<E> combo;

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

   protected void addListeners(final ItemListener itemListener) {
      this.combo.addItemListener(itemListener);
   }

   protected void atualizarLista(final Collection<E> novaLista) {
      this.combo.removeAllItems();
      novaLista.forEach(this.combo::addItem);
   }
}
