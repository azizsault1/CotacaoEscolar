package swingView;

import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Optional;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.app.exceptions.IllegalError;
import swingView.interfaces.Label;
import swingView.interfaces.LabelFieldConfiguration;
import swingView.interfaces.Posicao;

public class LabelFieldSeries extends LabelField<String> {

   private static final long serialVersionUID = 1L;

   public interface EventoSerieSelecionada {
      public void serieSelecionada(String serie) throws FoiNao;

      public void maisSeries(String serie) throws FoiNao;
   }

   public LabelFieldSeries(final Integer linha1, final EventoSerieSelecionada serieSelecionada) {
      super(LabelFieldConfiguration.Factory.create(Posicao.Factory.create(Dimensoes.MarginColuna2.getValor(), linha1), Label.Factory.create(50, "Series:")));
      super.addListeners(e -> {
         if (ItemEvent.SELECTED == e.getStateChange()) {
            final String serieEscolhida = (String) e.getItem();
            try {
               serieSelecionada.serieSelecionada(serieEscolhida);
            } catch (final FoiNao e1) {
               final JOptionPane optionPane = new JOptionPane("Deu alguma coisa errada que eu não consigo identificar o que foi.", JOptionPane.ERROR_MESSAGE);
               final JDialog dialog = optionPane.createDialog("Opppssss");
               dialog.setAlwaysOnTop(true);
               dialog.setVisible(true);
            }
         }
      });

      super.botaUmAe.addActionListener(action -> {
         final String digitado = JOptionPane.showInputDialog("Qual a Série?");
         try {
            serieSelecionada.maisSeries(digitado);
         } catch (final IllegalError | FoiNao e) {
            final JOptionPane optionPane = new JOptionPane(e.getMessage(), JOptionPane.ERROR_MESSAGE);
            final JDialog dialog = optionPane.createDialog("Opppssss");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
         } catch (final Exception e2) {
            final JOptionPane optionPane = new JOptionPane("Deu alguma coisa errada que eu não consigo identificar o que foi.", JOptionPane.ERROR_MESSAGE);
            final JDialog dialog = optionPane.createDialog("Opppssss");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
         }
      });

   }

   public void atualiarSerie(final List<String> series) {
      super.atualizarLista(series);
   }

   public Optional<String> getSerieEscolhida() {
      if (super.combo.getSelectedItem() != null) {
         return Optional.of((String) this.combo.getSelectedItem());
      }
      return Optional.empty();

   }

   public void selecionaSerie(final String serie) {
      this.combo.setSelectedItem(serie);
   }

}
