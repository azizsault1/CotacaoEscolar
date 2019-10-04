package cotacaoEscolar.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.service.ServicoDescricaoMaterialEscolar;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoListaMaterial;

public class ControllerBuscaSwing {

   private final ServicoEscola servicoEscola;
   private final ServicoListaMaterial servicoListaMaterial;
   private final ServicoDescricaoMaterialEscolar servicoDescricaoMaterialEscolar;

   public ControllerBuscaSwing(final ServicoEscola servicoEscola, final ServicoListaMaterial servicoListaMaterial,
         final ServicoDescricaoMaterialEscolar servicoDescricaoMaterialEscolar) {
      this.servicoEscola = servicoEscola;
      this.servicoListaMaterial = servicoListaMaterial;
      this.servicoDescricaoMaterialEscolar = servicoDescricaoMaterialEscolar;
   }

   public Collection<Escola> todasEscolas() {
      final List<Escola> escolas = new ArrayList<>(this.servicoEscola.todas());
      Collections.sort(escolas);
      return escolas;
   }

   public Collection<ListaMaterial> selecioneMaterialPor(final Escola escola) {
      return this.servicoListaMaterial.selecionePor(escola);
   }

   public ListaMaterial selecioneMaterialPor(final Escola escola, final String serie) throws FoiNao {
      return this.servicoListaMaterial.selecionePor(escola, serie);
   }

   public void remover(final Escola escola, final String serie, final Item item) throws FoiNao {
      this.servicoListaMaterial.remover(escola, serie, item);
   }

   public void adicionar(final Escola escola, final String serie, final Item item) throws FoiNao {
      this.servicoListaMaterial.adicionar(escola, serie, item);
   }

   public Collection<DescricaoMaterialEscolar> todasDescricoes() {
      return this.servicoDescricaoMaterialEscolar.todasDescricoes();
   }

}
