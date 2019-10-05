package swingView;

import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Optional;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.v1.Serie;
import swingView.interfaces.Label;
import swingView.interfaces.LabelFieldConfiguration;
import swingView.interfaces.Posicao;

public class LabelFieldSeries extends LabelField<Serie> {

   private static final long serialVersionUID = 1L;

   public interface EventoSerieSelecionada {
      public void serieSelecionada(Serie serie) throws FoiNao;

      public void maisSeries(Serie serie) throws FoiNao;
   }

   public LabelFieldSeries(final Integer linha1, final EventoSerieSelecionada serieSelecionada) {
      super(LabelFieldConfiguration.Factory.create(Posicao.Factory.create(Dimensoes.MarginColuna2.getValor(), linha1), Label.Factory.create(50, "Series:")));
      super.addListeners(e -> {
         if (ItemEvent.SELECTED == e.getStateChange()) {
            final String serieEscolhida = (String) e.getItem();
            try {
               final Serie escolhida = Serie.create(serieEscolhida);
               serieSelecionada.serieSelecionada(escolhida);
            } catch (final Exception e1) {
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
            final Serie serie = Serie.create(digitado);
            serieSelecionada.maisSeries(serie);
         } catch (final IllegalError e) {
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

   public void atualiarSerie(final List<Serie> series) {
      super.atualizarLista(series);
   }

   public Optional<Serie> getSerieEscolhida() {
      if (super.combo.getSelectedItem() != null) {
         return Optional.of((Serie) this.combo.getSelectedItem());
      }
      return Optional.empty();

   }

   public void selecionaSerie(final Serie serie) {
      this.combo.setSelectedItem(serie);
   }

}
