package swingView;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cotacaoEscolar.model.v1.ItemImpl;
import swingView.CustomOptionalPalne.AcaoBotoes;

public class TabelaMaterialEscolar {

   private final DefaultTableModel modelo;
   private final JTable tabela;
   private List<ItemImpl> itens;

   public TabelaMaterialEscolar() {

      this.modelo = new DefaultTableModel() {
         private static final long serialVersionUID = 1L;

         @Override
         public boolean isCellEditable(final int row, final int column) {
            return false;
         }
      };

      this.modelo.addColumn("Item");
      this.modelo.addColumn("Quantidade");
      this.tabela = new JTable(this.modelo);

      final AcaoBotoes acao = new AcaoBotoes() {

         @Override
         public void salvar(final ItemImpl de, final ItemImpl para) {
            TabelaMaterialEscolar.this.queroItem(de).ifPresent(itemARemover -> TabelaMaterialEscolar.this.removeItem(itemARemover));
            TabelaMaterialEscolar.this.itens.add(para);
            TabelaMaterialEscolar.this.atualizar();
         }

         @Override
         public void remover(final ItemImpl item) {
            TabelaMaterialEscolar.this.queroItem(item).ifPresent(itemARemover -> TabelaMaterialEscolar.this.removeItem(itemARemover));
            TabelaMaterialEscolar.this.atualizar();
         }
      };

      this.tabela.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(final MouseEvent e) {
            final int row = TabelaMaterialEscolar.this.tabela.rowAtPoint(e.getPoint());
            final int col = 0;
            final ItemImpl item = (ItemImpl) TabelaMaterialEscolar.this.tabela.getValueAt(row, col);
            CustomOptionalPalne.displayGUI(item, acao);
         }
      });
   }

   private void atualizar() {
      this.modelo.setNumRows(0);
      for (final ItemImpl item : this.itens) {
         this.modelo.addRow(new Object[] { item, item.getQuantidade() });
      }
   }

   public Component getTable() {
      return this.tabela;
   }

   public List<ItemImpl> getItens() {
      return this.itens;
   }

   public Optional<ItemImpl> queroItem(final ItemImpl item) {
      return this.itens.stream().filter(itemDaLista -> itemDaLista.equals(item)).findAny();
   }

   private void removeItem(final ItemImpl item) {
      this.itens.remove(item);
      this.atualizar();
   }

   public void atualizar(final List<ItemImpl> itens) {
      this.itens = new ArrayList<>(itens);
      this.atualizar();
   }

   public void adicioneMaisUmItem(final ItemImpl item) {
      final Optional<ItemImpl> opt = this.queroItem(item);

      final ItemImpl para;
      if (opt.isPresent()) {
         final ItemImpl itemEncontrado = opt.get();
         Integer novaQuantidade = itemEncontrado.getQuantidade() + 1;
         if (novaQuantidade > 100) {
            novaQuantidade = 100;
         }
         para = new ItemImpl(itemEncontrado.getMaterialEscolar(), novaQuantidade);
      } else {
         para = new ItemImpl(item.getMaterialEscolar(), 1);
      }

      opt.ifPresent(itemParaRemover -> this.removeItem(itemParaRemover));
      this.itens.add(para);
      this.atualizar();
   }

}
