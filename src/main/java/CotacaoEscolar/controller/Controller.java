package cotacaoEscolar.controller;

import java.util.List;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.ListaItem;
import cotacaoEscolar.model.ListaMaterial;

public interface Controller {

   List<Escola> todasEscolas();

   List<ListaMaterial> selecioneMaterialPor(Escola escola);

   ListaItem selecioneMaterialPor(Escola escola, Integer serie);

   void remover(Escola escola, Integer serie, Item item);

   void adicionar(Escola escola, Integer serie, Item item);

   List<DescricaoMaterialEscolar> todasDescricoes();

}