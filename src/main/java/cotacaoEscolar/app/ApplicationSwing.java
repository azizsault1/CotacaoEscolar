package cotacaoEscolar.app;

import java.io.IOException;
import java.security.GeneralSecurityException;

import cotacaoEscolar.controller.ControllerAlteracaoSwing;
import cotacaoEscolar.controller.ControllerBuscaSwing;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.repository.LocalDb;
import cotacaoEscolar.repository.json.JsonRepository;
import cotacaoEscolar.service.ServicoCotacao;
import cotacaoEscolar.service.ServicoDescricaoMaterialEscolar;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoEscolaLocal;
import cotacaoEscolar.service.ServicoEstabelecimento;
import cotacaoEscolar.service.ServicoListaMaterial;
import cotacaoEscolar.service.impl.ServicoCotacaoLocal;
import cotacaoEscolar.service.impl.ServicoDescricaoMaterialEscolarImpl;
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
      final ServicoCotacao servicoCotacao = new ServicoCotacaoLocal();
      final ServicoEstabelecimento servicoProduto = new ServicoEstabelecimentoLocal(oowww.meDaUmBancoDeestabelecimentos());
      final ServicoDescricaoMaterialEscolar servicoDescricaoMaterialEscolar = new ServicoDescricaoMaterialEscolarImpl(oowww.meDaUmBancoDeMaterial());

      final ControllerBuscaSwing controller = new ControllerBuscaSwing(servicoEscola, servicoListaMaterial, servicoDescricaoMaterialEscolar);
      final ControllerAlteracaoSwing controllerCotacao = new ControllerAlteracaoSwing(servicoCotacao, servicoProduto, servicoEscola, servicoListaMaterial);

      final ListaMaterial material = ListaMaterial.meDaUmMaterial(servicoEscola, servicoListaMaterial, servicoDescricaoMaterialEscolar);

      new Janela(controller, controllerCotacao, material);
   }

}
