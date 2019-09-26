package swingView;

import java.awt.event.ItemEvent;
import java.util.Collection;
import java.util.Optional;

import cotacaoEscolar.model.Escola;
import swingView.interfaces.Label;
import swingView.interfaces.LabelFieldConfiguration;
import swingView.interfaces.Posicao;

public class LabelFieldEscola extends LabelField<Escola> {

   private static final long serialVersionUID = 1L;

   public interface EventoEscolaSelecionada {
      public void escolaSelecionada(Escola escola);
   }

   public LabelFieldEscola(final int linha1, final EventoEscolaSelecionada escolaSelecionada) {
      super(LabelFieldConfiguration.Factory.create(Posicao.Factory.create(Dimensoes.MarginColuna1.getValor(), linha1), Label.Factory.create(70, "Colégio:")));
      super.addListeners(e -> {
         if (ItemEvent.SELECTED == e.getStateChange()) {
            final Escola escolaEscolhida = (Escola) e.getItem();
            escolaSelecionada.escolaSelecionada(escolaEscolhida);
         }
      });
   }

   public Optional<Escola> getEscolaEscolhida() {
      if (super.combo.getSelectedItem() != null) {
         return Optional.of((Escola) super.combo.getSelectedItem());
      }

      return Optional.empty();

   }

   public void atualizar(final Collection<Escola> escolas) {
      super.atualizarLista(escolas);
   }

}
