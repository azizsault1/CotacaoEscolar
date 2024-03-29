package cotacaoEscolar.app;

import java.io.IOException;
import java.security.GeneralSecurityException;

import cotacaoEscolar.controller.ControllerAlteracaoSwing;
import cotacaoEscolar.controller.ControllerBuscaSwing;
import cotacaoEscolar.repository.LocalDb;
import cotacaoEscolar.repository.json.JsonRepository;
import cotacaoEscolar.service.ServicoCotacao;
import cotacaoEscolar.service.ServicoDescricaoMaterialEscolar;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoEscolaLocal;
import cotacaoEscolar.service.ServicoEstabelecimento;
import cotacaoEscolar.service.ServicoListaMaterial;
import cotacaoEscolar.service.impl.ServicoCotacaoLocal;
import cotacaoEscolar.service.impl.ServicoDescricaoMaterialEscolarLocal;
import cotacaoEscolar.service.impl.ServicoEstabelecimentoLocal;
import cotacaoEscolar.service.impl.ServicoListaMaterialLocal;
import swingView.Janela;

public class ApplicationSwing {
   enum TipoDessaPorra {
      LOCAL,
      JSON;
   }

   public static void main(final String[] args) throws IOException, GeneralSecurityException {
      final TipoDessaPorra bancoEscolhido = TipoDessaPorra.JSON;

      final EscolhaUmBancoNessaPorra oowww;
      switch (bancoEscolhido) {
      case JSON:
         oowww = new JsonRepository("src/main/resources/dbfiles/");
         break;
      default:
         oowww = new LocalDb();
      }

      final ServicoEscola servicoEscola = new ServicoEscolaLocal(oowww.meDaUmBancoDeEscola());
      final ServicoListaMaterial servicoListaMaterial = new ServicoListaMaterialLocal(oowww.meDaUmBancoDeListaMaterial());
      final ServicoEstabelecimento servicoProduto = new ServicoEstabelecimentoLocal(oowww.meDaUmBancoDeestabelecimentos());
      final ServicoDescricaoMaterialEscolar material = new ServicoDescricaoMaterialEscolarLocal(oowww.meDaUmBancoDeMaterial());
      final ServicoCotacao servicoCotacao = new ServicoCotacaoLocal();

      final ControllerBuscaSwing controller = new ControllerBuscaSwing(servicoEscola, servicoListaMaterial, material);
      final ControllerAlteracaoSwing controllerCotacao = new ControllerAlteracaoSwing(servicoCotacao, servicoProduto, servicoEscola, servicoListaMaterial);
      new Janela(controller, controllerCotacao);
   }

}
