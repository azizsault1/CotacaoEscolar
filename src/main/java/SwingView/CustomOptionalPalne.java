package SwingView;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import CotacaoEscolar.item.model.DescricaoMaterialEscolar;
import CotacaoEscolar.item.model.Item;

public class CustomOptionalPalne {

   private void displayGUI(final Item item) {
      final JOptionPane optionPane = new JOptionPane(null);
      optionPane.setLayout(null);
      optionPane.add(this.getPanel(item));
      final JDialog dialog = optionPane.createDialog(null, "Width 100");
      dialog.setSize(350, 300);
      dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      dialog.setVisible(true);
   }

   private JPanel getPanel(final Item item) {
      final JPanel panel = new JPanel();
      panel.setLayout(null);
      final JLabel label = new JLabel("Descricao:");
      label.setBounds(5, 5, 90, 20);
      panel.add(label);

      final JTextField descricao = new JTextField(item.toString());
      descricao.setBounds(3, 25, 120, 20);
      panel.add(descricao);

      final JLabel labelQuantidade = new JLabel("Quantidade:");
      labelQuantidade.setBounds(150, 5, 90, 20);
      panel.add(labelQuantidade);

      final JSpinner quantidade = new JSpinner();
      quantidade.setValue(item.getQuantidade());
      quantidade.setBounds(150, 25, 120, 20);
      panel.add(quantidade);

      panel.setBorder(BorderFactory.createLineBorder(Color.blue));
      panel.setBounds(3, 3, 343, 273);

      return panel;
   }

   public static void main(final String[] args) {
      final DescricaoMaterialEscolar descricao = new DescricaoMaterialEscolar("Lapis");
      final Item item = new Item(descricao, 10);
      SwingUtilities.invokeLater(() -> new CustomOptionalPalne().displayGUI(item));
   }
}