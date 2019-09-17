package app;

import cotacaoEscolar.controller.ControllerCotacao;
import cotacaoEscolar.controller.ControllerMaterialEscolar;
import cotacaoEscolar.repository.LocalDb;
import cotacaoEscolar.repository.Repository;
import cotacaoEscolar.service.ServicoCotacao;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoEscolaLocal;
import cotacaoEscolar.service.ServicoItem;
import cotacaoEscolar.service.ServicoListaMaterial;
import cotacaoEscolar.service.impl.ServicoCotacaoLocal;
import cotacaoEscolar.service.impl.ServicoEstabelecimentoLocal;
import cotacaoEscolar.service.impl.ServicoItemLocal;
import cotacaoEscolar.service.impl.ServicoListaMaterialLocal;
import swingView.Janela;

public class ApplicationSwing {

   public static void main(final String[] args) {
      final Repository repository = new LocalDb();
      final ServicoEscola servicoEscola = new ServicoEscolaLocal(repository);
      final ServicoItem servicoItem = new ServicoItemLocal(repository);
      final ServicoListaMaterial servicoListaMaterial = new ServicoListaMaterialLocal(repository);

      final ServicoCotacao servicoCotacao = new ServicoCotacaoLocal();
      final ServicoEstabelecimentoLocal servicoProduto = new ServicoEstabelecimentoLocal(repository);

      final ControllerMaterialEscolar controller = new ControllerMaterialEscolar(servicoEscola, servicoItem, servicoListaMaterial);
      final ControllerCotacao controllerCotacao = new ControllerCotacao(servicoCotacao, servicoProduto);
      new Janela(controller, controllerCotacao);
   }

}
