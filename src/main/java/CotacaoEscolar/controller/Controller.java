package cotacaoEscolar.controller;

import java.util.Collection;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.listas.ListaItem;
import cotacaoEscolar.model.listas.ListaMaterial;

public interface Controller {

   Collection<Escola> todasEscolas();

   Collection<ListaMaterial> selecioneMaterialPor(Escola escola);

   ListaItem selecioneMaterialPor(Escola escola, Integer serie);

   void remover(Escola escola, Integer serie, Item item);

   void adicionar(Escola escola, Integer serie, Item item);

   Collection<DescricaoMaterialEscolar> todasDescricoes();

}