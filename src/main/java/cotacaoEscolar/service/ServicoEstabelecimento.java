package cotacaoEscolar.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.Estabelecimento;
import cotacaoEscolar.model.listas.ListaEstabelecimento;

@Service
public interface ServicoEstabelecimento {

   ListaEstabelecimento todos();

   void salvar(Estabelecimento estabelecimento) throws FoiNao;

   Optional<Estabelecimento> selecionePor(Estabelecimento estabelecimento);

}
