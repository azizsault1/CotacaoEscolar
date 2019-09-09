package servicos;

import java.util.List;

import CotacaoEscolar.escola.model.Escola;
import CotacaoEscolar.escola.service.ServicoEscola;
import CotacaoEscolar.escola.service.ServicoEscolaLocal;
import CotacaoEscolar.item.service.ServicoItem;
import CotacaoEscolar.item.service.ServicoItemLocal;

public class ListaServicos {

   private final ServicoEscola servicoEscola;
   private final ServicoListaMaterial servicoListaMaterial;
   private final ServicoItem servicoItem;

   public ListaServicos() {
      this.servicoEscola = new ServicoEscolaLocal();
      final List<Escola> escolas = this.servicoEscola.todas();
      this.servicoItem = new ServicoItemLocal();
      this.servicoListaMaterial = new ServicoListaMaterialLocal(escolas, this.servicoItem);
   }

   public ServicoEscola getServicoEscola() {
      return this.servicoEscola;
   }

   public ServicoListaMaterial getServicoListaMaterial() {
      return this.servicoListaMaterial;
   }

   public ServicoItem getServicoItem() {
      return this.servicoItem;
   }
}
