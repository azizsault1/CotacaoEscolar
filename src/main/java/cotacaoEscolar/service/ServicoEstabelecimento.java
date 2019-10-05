package cotacaoEscolar.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.v1.Estabelecimento;
import cotacaoEscolar.model.v1.ListaEstabelecimento;

@Service
public interface ServicoEstabelecimento {

   ListaEstabelecimento todos();

   void salvar(Estabelecimento estabelecimento) throws FoiNao;

   Optional<Estabelecimento> selecionePor(Estabelecimento estabelecimento);

}
