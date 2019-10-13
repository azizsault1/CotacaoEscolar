package cotacaoEscolar.app;

import java.io.IOException;
import java.security.GeneralSecurityException;

import cotacaoEscolar.controller.EscolasController;
import cotacaoEscolar.controller.EstabelecimentosController;
import cotacaoEscolar.model.DescricoesMaterialEscolar;
import cotacaoEscolar.model.Escolas;
import cotacaoEscolar.model.Estabelecimentos;
import cotacaoEscolar.model.ListaMateriaisEscolares;
import cotacaoEscolar.repository.LocalDb;
import cotacaoEscolar.repository.json.JsonRepository;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoEscolaLocal;
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

      final DescricoesMaterialEscolar descricoes = DescricoesMaterialEscolar.create(oowww.meDaUmBancoDeMaterial());
      final ListaMateriaisEscolares materiais = ListaMateriaisEscolares.create(oowww.meDaUmBancoDeListaMaterial());
      final Estabelecimentos estabelecimentos = new EstabelecimentosController(oowww.meDaUmBancoDeEstabelecimentos());
      final Escolas escolas = new EscolasController(servicoEscola);

      new Janela(escolas, materiais, descricoes, estabelecimentos);
   }

}
