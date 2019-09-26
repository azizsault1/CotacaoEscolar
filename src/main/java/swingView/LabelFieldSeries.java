package swingView;

import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Optional;

import swingView.interfaces.Label;
import swingView.interfaces.LabelFieldConfiguration;
import swingView.interfaces.Posicao;

public class LabelFieldSeries extends LabelField<Integer> {

   private static final long serialVersionUID = 1L;

   public interface EventoSerieSelecionada {
      public void serieSelecionada(Integer serie);
   }

   public LabelFieldSeries(final Integer linha1, final EventoSerieSelecionada serieSelecionada) {
      super(LabelFieldConfiguration.Factory.create(Posicao.Factory.create(Dimensoes.MarginColuna2.getValor(), linha1), Label.Factory.create(50, "Series:")));
      super.addListeners(e -> {
         if (ItemEvent.SELECTED == e.getStateChange()) {
            final Integer serieEscolhida = (Integer) e.getItem();
            serieSelecionada.serieSelecionada(serieEscolhida);
         }
      });
   }

   public void atualiarSerie(final List<Integer> series) {
      super.atualizarLista(series);
   }

   public Optional<Integer> getSerieEscolhida() {
      if (super.combo.getSelectedItem() != null) {
         return Optional.of((Integer) this.combo.getSelectedItem());
      }
      return Optional.empty();

   }

}
