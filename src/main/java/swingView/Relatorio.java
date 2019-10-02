package swingView;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cotacaoEscolar.model.v1.ResultadoCotacao;

public class Relatorio {
   private static final Integer LARGURA_TELA = 400;
   private static final Integer MEIO = 2;

   public static void displayGUI(final ResultadoCotacao resultado) {
      final JOptionPane optionPane = new JOptionPane(null);
      optionPane.setLayout(null);
      final JDialog dialog = optionPane.createDialog(null, "Item");
      optionPane.add(getPanel(resultado, dialog));
      dialog.setSize(LARGURA_TELA, 500);
      dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      dialog.setVisible(true);
   }

   private static JPanel getPanel(final ResultadoCotacao resultado, final JDialog dialog) {
      final JLabel label = new JLabel("Relatorio");
      label.setBounds(5, 5, 90, 20);

      final JTextArea descricao = new JTextArea(resultado.toReport());

      descricao.setAutoscrolls(true);
      descricao.setWrapStyleWord(true);
      descricao.setLineWrap(true);

      final JButton ok = new JButton("OK");
      final int LARGURA_OK = 100;
      ok.addActionListener(listener -> dialog.dispose());
      ok.setBounds((LARGURA_TELA / MEIO) - (LARGURA_OK / MEIO), 410, LARGURA_OK, 25);

      final JScrollPane js = new JScrollPane(descricao);
      final int LARGURA_TEXTO = 350;
      js.setBounds((LARGURA_TELA / MEIO) - (LARGURA_TEXTO / MEIO), 25, LARGURA_TEXTO, 370);

      final JPanel panel = new JPanel();
      panel.setLayout(null);
      panel.add(ok);
      panel.add(label);
      panel.add(js);
      panel.setBounds(3, 3, 395, 475);
      return panel;
   }

}