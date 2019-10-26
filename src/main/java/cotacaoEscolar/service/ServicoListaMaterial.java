package cotacaoEscolar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.listas.ListaMaterial;

@Service
public interface ServicoListaMaterial {

   ListaMaterial salvar(Escola escola, String serie) throws FoiNao;

   Collection<ListaMaterial> selecionePor(Escola escola);

   ListaMaterial selecionePor(Escola escolaEscolhida, String serieEscolhida) throws FoiNao;

   void remover(Escola escola, String serie, Item de) throws FoiNao;

   void adicionar(Escola escola, String serie, Item para) throws FoiNao;

   Collection<String> selecioneSeriesPor(Escola escolaEncontrada);

   void alterarQuantidade(Escola escolaModel, String serie, Item item) throws FoiNao;

}
