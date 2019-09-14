package servicos;

import java.util.List;

import cotacaoEscolar.escola.model.Escola;
import cotacaoEscolar.escola.service.ServicoEscola;
import cotacaoEscolar.escola.service.ServicoEscolaLocal;
import cotacaoEscolar.item.service.ServicoItem;
import cotacaoEscolar.item.service.ServicoItemLocal;
import cotacaoEscolar.materialEscolar.servico.ServicoListaMaterial;
import cotacaoEscolar.materialEscolar.servico.ServicoListaMaterialLocal;

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
