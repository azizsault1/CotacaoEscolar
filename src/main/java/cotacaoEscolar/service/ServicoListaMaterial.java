package cotacaoEscolar.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.listas.ListaMaterial;

@Service
public interface ServicoListaMaterial {

   ListaMaterial salvar(Escola escola, String serie);

   Collection<ListaMaterial> selecionePor(Escola escola);

   ListaMaterial selecionePor(Escola escolaEscolhida, String serieEscolhida);

   void remover(Escola escola, String serie, Item de);

   void adicionar(Escola escola, String serie, Item para);

   Collection<String> selecioneSeriesPor(Escola escolaEncontrada);

}
