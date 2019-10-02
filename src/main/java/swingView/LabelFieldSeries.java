package swingView;

import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Optional;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import cotacaoEscolar.app.IllegalError;
import cotacaoEscolar.model.v1.Serie;
import swingView.interfaces.Label;
import swingView.interfaces.LabelFieldConfiguration;
import swingView.interfaces.Posicao;

public class LabelFieldSeries extends LabelField<Serie> {

   private static final long serialVersionUID = 1L;

   public interface EventoSerieSelecionada {
      public void serieSelecionada(Serie serie);

      public void maisSeries(Serie serie);
   }

   public LabelFieldSeries(final Integer linha1, final EventoSerieSelecionada serieSelecionada) {
      super(LabelFieldConfiguration.Factory.create(Posicao.Factory.create(Dimensoes.MarginColuna2.getValor(), linha1), Label.Factory.create(50, "Series:")));
      super.addListeners(e -> {
         if (ItemEvent.SELECTED == e.getStateChange()) {
            final Serie serieEscolhida = (Serie) e.getItem();
            serieSelecionada.serieSelecionada(serieEscolhida);
         }
      });

      super.botaUmAe.addActionListener(action -> {
         final String digitado = JOptionPane.showInputDialog("Qual a Série?");
         try {
            serieSelecionada.maisSeries(Serie.create(digitado));
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
