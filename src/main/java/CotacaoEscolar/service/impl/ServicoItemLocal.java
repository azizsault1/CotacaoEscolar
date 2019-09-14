package cotacaoEscolar.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.ListaItem;
import cotacaoEscolar.service.ServicoItem;

@Service
public class ServicoItemLocal implements ServicoItem {

   private final List<DescricaoMaterialEscolar> itens;
   private final Map<String, ListaItem> listasEstaticas;

   public ServicoItemLocal() {
      final DescricaoMaterialEscolar lapisDesc = new DescricaoMaterialEscolar("Lapis 123");
      final Item lapis = new Item(lapisDesc, 5);

      final DescricaoMaterialEscolar lapisDeCorDesc = new DescricaoMaterialEscolar("Lapis de cor");
      final Item lapisDeCor = new Item(lapisDeCorDesc, 30);

      final DescricaoMaterialEscolar classificadorDesc = new DescricaoMaterialEscolar("Classificador");
      final Item classificador = new Item(classificadorDesc, 2);

      final DescricaoMaterialEscolar cadernoDesc = new DescricaoMaterialEscolar("Caderno");
      final Item caderno = new Item(cadernoDesc, 1);

      this.itens = Arrays.asList(lapisDesc, lapisDeCorDesc, classificadorDesc, cadernoDesc);

      this.listasEstaticas = new HashMap<>();

      final ListaItem lista1 = new ListaItem(lapis, lapisDeCor);
      final ListaItem lista2 = new ListaItem(lapis, classificador);
      final ListaItem lista3 = new ListaItem(caderno, classificador);
      this.listasEstaticas.put("Lista1", lista1);
      this.listasEstaticas.put("Lista2", lista2);
      this.listasEstaticas.put("Lista3", lista3);

   }

   @Override
   public ListaItem selecionePor(final int serie) {
      switch (serie) {
      case 1:
         return this.listasEstaticas.get("Lista1");
      case 2:
         return this.listasEstaticas.get("Lista2");
      default:
         return this.listasEstaticas.get("Lista3");
      }
   }

   @Override
   public List<DescricaoMaterialEscolar> todasDescricoes() {
      return this.itens;
   }

}
