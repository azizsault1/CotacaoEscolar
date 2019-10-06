package cotacaoEscolar.app;

import cotacaoEscolar.repository.DescricaoMaterialEscolarRepository;
import cotacaoEscolar.repository.EscolaRepository;
import cotacaoEscolar.repository.EstabelecimentoRepository;
import cotacaoEscolar.repository.ListaMaterialRepository;

public interface EscolhaUmBancoNessaPorra {
   DescricaoMaterialEscolarRepository meDaUmBancoDeMaterial();

   EscolaRepository meDaUmBancoDeEscola();

   ListaMaterialRepository meDaUmBancoDeListaMaterial();

   EstabelecimentoRepository meDaUmBancoDeEstabelecimentos();
}
