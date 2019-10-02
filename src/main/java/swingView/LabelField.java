package swingView;

import java.awt.event.ItemListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import swingView.interfaces.LabelFieldConfiguration;

public class LabelField<E> extends JPanel {
   private static final long serialVersionUID = 1L;
   private static final int ESPACO = 3;
   private static final int ALTURA_COMPONENTES_PADRAO = Dimensoes.AlturaComponentPadrao.getValor();
   private static final int LARGURA_COMPONENTE_PADRAO = Dimensoes.LarguraLabel.getValor();
   private static final int LARGURA_BOTAO_MAIS = 30;

   private final LabelFieldConfiguration conf;
   protected final JComboBox<E> combo;

   protected JButton botaUmAe;

   public LabelField(final LabelFieldConfiguration conf) {
      this.conf = conf;
      this.combo = new JComboBox<>();
      this.setLayout(null);
      this.addComponents();
   }

   private void addComponents() {
      final JLabel label = new JLabel(this.conf.labelText());
      label.setBounds(0, 0, this.conf.labelWidth(), ALTURA_COMPONENTES_PADRAO);

      final int larguraDoLabelField = label.getWidth() + ESPACO + LARGURA_COMPONENTE_PADRAO;
      final int larguraCombo = larguraDoLabelField - (ESPACO + this.conf.labelWidth() + ESPACO + LARGURA_BOTAO_MAIS);
      final int margemDoCobo = this.conf.labelWidth() + ESPACO;

      this.combo.setBounds(margemDoCobo, 0, larguraCombo, ALTURA_COMPONENTES_PADRAO);

      this.botaUmAe = new JButton("+");
      this.botaUmAe.setBounds(margemDoCobo + larguraCombo + ESPACO, 0, LARGURA_BOTAO_MAIS, ALTURA_COMPONENTES_PADRAO);

      this.add(this.botaUmAe);
      this.add(label);
      this.add(this.combo);

      this.setBounds(this.conf.x(), this.conf.y(), larguraDoLabelField, ALTURA_COMPONENTES_PADRAO);
   }

   protected void addListeners(final ItemListener itemListener) {
      this.combo.addItemListener(itemListener);
   }

   protected void atualizarLista(final Collection<E> novaLista) {
      this.combo.removeAllItems();
      novaLista.forEach(this.combo::addItem);
   }

}
