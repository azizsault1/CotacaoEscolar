package cotacaoEscolar.controller;

import java.util.List;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.ListaItem;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoItem;
import cotacaoEscolar.service.ServicoListaMaterial;

public class ControllerMaterialEscolar implements Controller {

   private final ServicoEscola servicoEscola;
   private final ServicoListaMaterial servicoListaMaterial;
   private final ServicoItem servicoItem;

   public ControllerMaterialEscolar(final ServicoEscola servicoEscola, final ServicoItem servicoItem, final ServicoListaMaterial servicoListaMaterial) {
      this.servicoEscola = servicoEscola;
      this.servicoItem = servicoItem;
      this.servicoListaMaterial = servicoListaMaterial;
   }

   @Override
   public List<Escola> todasEscolas() {
      return this.servicoEscola.todas();
   }

   @Override
   public List<ListaMaterial> selecioneMaterialPor(final Escola escola) {
      return this.servicoListaMaterial.selecionePor(escola);
   }

   @Override
   public ListaItem selecioneMaterialPor(final Escola escola, final Integer serie) {
      return this.servicoListaMaterial.selecionePor(escola, serie);
   }

   @Override
   public void remover(final Escola escola, final Integer serie, final Item item) {
      this.servicoListaMaterial.remover(escola, serie, item);
   }

   @Override
   public void adicionar(final Escola escola, final Integer serie, final Item item) {
      this.servicoListaMaterial.adicionar(escola, serie, item);
   }

   @Override
   public List<DescricaoMaterialEscolar> todasDescricoes() {
      return this.servicoItem.todasDescricoes();
   }
}