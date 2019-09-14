package cotacaoEscolar.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.ListaItem;
import cotacaoEscolar.model.ListaMaterial;

@org.springframework.stereotype.Repository
public class LocalDb implements Repository {
   private Set<Escola> escolas;
   private List<DescricaoMaterialEscolar> itens;
   private Map<String, ListaItem> listasEstaticas;
   private final List<ListaMaterial> listaMaterialEscolar = new ArrayList<>();

   public LocalDb() {
      this.initEscolas();
   }

   private void initEscolas() {
      final Escola escola1 = new Escola("Escola1");
      final Escola escola2 = new Escola("Escola2");
      final Escola escola3 = new Escola("Escola3");

      this.escolas = new HashSet<>(Arrays.asList(escola1, escola2, escola3));
      this.initItens();
      this.initMateriais(escola1, escola2, escola3);
   }

   private void initItens() {

      final DescricaoMaterialEscolar lapisDesc = new DescricaoMaterialEscolar("Lapis 123");
      final Item lapis = new Item(lapisDesc, 5);

      final DescricaoMaterialEscolar lapisDeCorDesc = new DescricaoMaterialEscolar("Lapis de cor");
      final Item lapisDeCor = new Item(lapisDeCorDesc, 30);

      final DescricaoMaterialEscolar classificadorDesc = new DescricaoMaterialEscolar("Classificador");
      final Item classificador = new Item(classificadorDesc, 2);

      final DescricaoMaterialEscolar cadernoDesc = new DescricaoMaterialEscolar("Caderno");
      final Item caderno = new Item(cadernoDesc, 1);

      this.itens = Arrays.asList(lapisDesc, lapisDeCorDesc, classificadorDesc, cadernoDesc);

      final ListaItem lista1 = new ListaItem(lapis, lapisDeCor);
      final ListaItem lista2 = new ListaItem(lapis, classificador);
      final ListaItem lista3 = new ListaItem(caderno, classificador);

      this.listasEstaticas = new HashMap<>();
      this.listasEstaticas.put("Lista1", lista1);
      this.listasEstaticas.put("Lista2", lista2);
      this.listasEstaticas.put("Lista3", lista3);
   }

   private void initMateriais(final Escola escola1, final Escola escola2, final Escola escola3) {

      // Escola1
      this.listaMaterialEscolar.add(new ListaMaterial(escola1, Integer.valueOf(1), this.selecionePor(1)));
      this.listaMaterialEscolar.add(new ListaMaterial(escola1, Integer.valueOf(2), this.selecionePor(2)));
      this.listaMaterialEscolar.add(new ListaMaterial(escola1, Integer.valueOf(3), this.selecionePor(3)));
      // Escola2
      this.listaMaterialEscolar.add(new ListaMaterial(escola2, Integer.valueOf(3), this.selecionePor(3)));
      this.listaMaterialEscolar.add(new ListaMaterial(escola2, Integer.valueOf(2), this.selecionePor(2)));
      this.listaMaterialEscolar.add(new ListaMaterial(escola2, Integer.valueOf(1), this.selecionePor(1)));

      // Escola3
      this.listaMaterialEscolar.add(new ListaMaterial(escola3, Integer.valueOf(2), this.selecionePor(2)));
      this.listaMaterialEscolar.add(new ListaMaterial(escola3, Integer.valueOf(1), this.selecionePor(1)));
      this.listaMaterialEscolar.add(new ListaMaterial(escola3, Integer.valueOf(4), this.selecionePor(4)));
   }

   @Override
   public Collection<Escola> escolas() {
      return this.escolas;
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

   @Override
   public Collection<ListaMaterial> materiais() {
      return this.listaMaterialEscolar;
   }

   @Override
   public void add(final ListaMaterial novaLista) {
      this.listaMaterialEscolar.add(novaLista);
   }

}
