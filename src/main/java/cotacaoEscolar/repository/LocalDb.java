package cotacaoEscolar.repository;

import cotacaoEscolar.model.*;
import cotacaoEscolar.model.listas.ListaEstabelecimento;
import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.model.listas.ListaProduto;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class LocalDb implements Repository {
   private Set<Escola> escolas;
   private Set<DescricaoMaterialEscolar> itens;
   private Map<String, List<Item>> listasEstaticas;
   private final List<ListaMaterial> listaMaterialEscolar = new ArrayList<>();
   private ListaEstabelecimento estabelecimentos;

   private Set<Produto> produtos;

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
      this.initProdutos();
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

      final List<DescricaoMaterialEscolar> itensFabricados = DescricaoMaterialEscolar.create(20);

      this.itens = new HashSet<>(Arrays.asList(lapisDesc, lapisDeCorDesc, classificadorDesc, cadernoDesc));
      this.itens.addAll(itensFabricados);

      final List<Item> lista1 = Arrays.asList(lapis, lapisDeCor);
      final List<Item> lista2 = Arrays.asList(lapis, classificador);
      final List<Item> lista3 = Arrays.asList(caderno, classificador);

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

   private void initProdutos() {
      final List<DescricaoMaterialEscolar> listaItens = this.itens.stream().collect(Collectors.toList());
      final Produto produto1 = new Produto(listaItens.get(0), BigDecimal.valueOf(2.0));
      final Produto produto2 = new Produto(listaItens.get(1), BigDecimal.valueOf(7.0));
      final Produto produto3 = new Produto(listaItens.get(2), BigDecimal.valueOf(17.0));
      final Produto produto4 = new Produto(listaItens.get(3), BigDecimal.valueOf(22.0));
      final Produto produto5 = new Produto(listaItens.get(4), BigDecimal.valueOf(74.0));
      final Produto produto6 = new Produto(listaItens.get(5), BigDecimal.valueOf(12.0));
      final Produto produto7 = new Produto(listaItens.get(6), BigDecimal.valueOf(25.0));
      final Produto produto8 = new Produto(listaItens.get(7), BigDecimal.valueOf(19.0));
      final Produto produto9 = new Produto(listaItens.get(8), BigDecimal.valueOf(33.0));
      final Produto produto10 = new Produto(listaItens.get(9), BigDecimal.valueOf(44.0));

      this.produtos = new HashSet<>(Arrays.asList(produto1, produto2, produto3, produto4, produto5, produto6, produto7, produto8, produto9, produto10));

      this.initEstabelecimentos();
   }

   private void initEstabelecimentos() {

      final List<Produto> todos = this.produtos.stream().collect(Collectors.toList());

      // Estabelecimento1
      final ListaProduto produtosEstabelecimento1 = new ListaProduto(todos.get(0), todos.get(1), todos.get(2));
      final Estabelecimento estabelecimento1 = new Estabelecimento("Estabelecimento1", produtosEstabelecimento1);

      // Estabelecimento2
      final ListaProduto produtosEstabelecimento2 = new ListaProduto(todos);
      final Estabelecimento estabelecimento2 = new Estabelecimento("Estabelecimento2", produtosEstabelecimento2);

      // Estabelecimento3
      final ListaProduto produtosEstabelecimento3 = new ListaProduto(todos.get(9), todos.get(8), todos.get(7), todos.get(6), todos.get(5), todos.get(4));
      final Estabelecimento estabelecimento3 = new Estabelecimento("Estabelecimento3", produtosEstabelecimento3);

      // Estabelecimento4
      final ListaProduto produtosEstabelecimento4 = new ListaProduto(todos.get(0), todos.get(2), todos.get(4), todos.get(6), todos.get(8));
      final Estabelecimento estabelecimento4 = new Estabelecimento("Estabelecimento4", produtosEstabelecimento4);

      // Estabelecimento5
      final ListaProduto produtosEstabelecimento5 = new ListaProduto(todos.get(1), todos.get(3), todos.get(5), todos.get(7), todos.get(9));
      final Estabelecimento estabelecimento5 = new Estabelecimento("Estabelecimento5", produtosEstabelecimento5);

      this.estabelecimentos = new ListaEstabelecimento(Arrays.asList(estabelecimento1, estabelecimento2, estabelecimento3, estabelecimento4, estabelecimento5));
   }

   @Override
   public Collection<Escola> escolas() {
      return this.escolas;
   }

   @Override
   public List<Item> selecionePor(final int serie) {
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
   public Collection<DescricaoMaterialEscolar> todasDescricoes() {
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

   @Override
   public ListaEstabelecimento estabelecimentos() {
      return this.estabelecimentos;
   }

}
