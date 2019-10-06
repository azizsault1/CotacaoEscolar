package cotacaoEscolar.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.v1.Estabelecimento;
import cotacaoEscolar.repository.EstabelecimentoRepository;
import cotacaoEscolar.service.ServicoEstabelecimento;

public class ServicoEstabelecimentoLocal implements ServicoEstabelecimento {

   private final EstabelecimentoRepository repository;

   @Autowired
   public ServicoEstabelecimentoLocal(final EstabelecimentoRepository repository) {
      this.repository = repository;
   }

   @Override
   public void salvar(final Estabelecimento estabelecimento) throws FoiNao {
      this.repository.salvaSaPorra(estabelecimento);
   }

   @Override
   public Optional<Estabelecimento> selecionePor(final Estabelecimento estabelecimento) {
      return this.repository.selecionePor(estabelecimento);
   }

}
