package cotacaoEscolar.controller;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.ListaEstabelecimento;
import cotacaoEscolar.model.v1.ResultadoCotacao;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.service.ServicoCotacao;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoEstabelecimento;
import cotacaoEscolar.service.ServicoListaMaterial;

public class ControllerAlteracaoSwing {

   private final ServicoCotacao servicoCotacao;
   private final ServicoEstabelecimento servicoEstabelecimento;
   private final ServicoEscola servicoEscola;
   private final ServicoListaMaterial servicoListaMaterial;

   public ControllerAlteracaoSwing(final ServicoCotacao servicoCotacao, final ServicoEstabelecimento servicoEstabelecimento, final ServicoEscola servicoEscola,
         final ServicoListaMaterial servicoListaMaterial) {
      this.servicoEscola = servicoEscola;
      this.servicoListaMaterial = servicoListaMaterial;
      this.servicoCotacao = servicoCotacao;
      this.servicoEstabelecimento = servicoEstabelecimento;
   }

   public ResultadoCotacao cotar(final ListaMaterial lista) {
      final ListaEstabelecimento estabelecimentos = this.servicoEstabelecimento.todos();
      return this.servicoCotacao.cotar(lista, estabelecimentos);
   }

   public ListaMaterial salvarSerie(final Escola escola, final Serie serie) throws FoiNao {
      return this.servicoListaMaterial.salvar(escola, serie);
   }

   public void salvarEscola(final Escola escola) throws FoiNao {
      this.servicoEscola.salvar(escola);
   }

}
