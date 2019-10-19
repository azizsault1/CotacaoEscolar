package cotacaoEscolar.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import cotacaoEscolar.app.EscolhaUmBancoNessaPorra;
import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolarImpl;
import cotacaoEscolar.model.v1.Estabelecimento;
import cotacaoEscolar.model.v1.ItemImpl;
import cotacaoEscolar.model.v1.ListaEstabelecimento;
import cotacaoEscolar.model.v1.ListaProduto;
import cotacaoEscolar.model.v1.ProdutoImpl;
import cotacaoEscolar.model.v1.Serie;

public class LocalDb implements EscolhaUmBancoNessaPorra {
   private Set<Escola> escolas;
   private Set<DescricaoMaterialEscolarImpl> itens;
   private Map<String, List<ItemImpl>> listasEstaticas;
   private final List<ListaMaterial> listaMaterialEscolar = new ArrayList<>();
   private ListaEstabelecimento estabelecimentos;

   private Set<ProdutoImpl> produtos;

   public LocalDb() {
      this.initEscolas();
   }

   private void initEscolas() {
      this.escolas = new HashSet<>();
      final Escola escola1 = Escola.create("Escola1");
      final Escola escola2 = Escola.create("Escola2");
      final Escola escola3 = Escola.create("Escola3");
      this.escolas.addAll(Arrays.asList(escola1, escola2, escola3));

      this.initItens();
      this.initMateriais(escola1, escola2, escola3);
      this.initProdutos();
   }

   private void initItens() {

      final DescricaoMaterialEscolarImpl lapisDesc = DescricaoMaterialEscolarImpl.create("Lapis 123");
      final ItemImpl lapis = new ItemImpl(lapisDesc, 5);

      final DescricaoMaterialEscolarImpl lapisDeCorDesc = DescricaoMaterialEscolarImpl.create("Lapis de cor");
      final ItemImpl lapisDeCor = new ItemImpl(lapisDeCorDesc, 30);

      final DescricaoMaterialEscolarImpl classificadorDesc = DescricaoMaterialEscolarImpl.create("Classificador");
      final ItemImpl classificador = new ItemImpl(classificadorDesc, 2);

      final DescricaoMaterialEscolarImpl cadernoDesc = DescricaoMaterialEscolarImpl.create("Caderno");
      final ItemImpl caderno = new ItemImpl(cadernoDesc, 1);

      final List<DescricaoMaterialEscolarImpl> itensFabricados = DescricaoMaterialEscolarImpl.create(20);

      this.itens = new HashSet<>(Arrays.asList(lapisDesc, lapisDeCorDesc, classificadorDesc, cadernoDesc));
      itensFabricados.forEach(itemEntrontrado -> this.itens.add(itemEntrontrado));

      final List<ItemImpl> lista1 = Arrays.asList(lapis, lapisDeCor);
      final List<ItemImpl> lista2 = Arrays.asList(lapis, classificador);
      final List<ItemImpl> lista3 = Arrays.asList(caderno, classificador);

      this.listasEstaticas = new HashMap<>();
      this.listasEstaticas.put("Lista1", lista1);
      this.listasEstaticas.put("Lista2", lista2);
      this.listasEstaticas.put("Lista3", lista3);
   }

   private void initMateriais(final Escola escola1, final Escola escola2, final Escola escola3) {

      // Escola1
      this.listaMaterialEscolar.add(ListaMaterial.create(escola1, Serie.create("1"), this.selecionePor(1)));
      this.listaMaterialEscolar.add(ListaMaterial.create(escola1, Serie.create("2"), this.selecionePor(2)));
      this.listaMaterialEscolar.add(ListaMaterial.create(escola1, Serie.create("3"), this.selecionePor(3)));
      // Escola2
      this.listaMaterialEscolar.add(ListaMaterial.create(escola2, Serie.create("3"), this.selecionePor(3)));
      this.listaMaterialEscolar.add(ListaMaterial.create(escola2, Serie.create("2"), this.selecionePor(2)));
      this.listaMaterialEscolar.add(ListaMaterial.create(escola2, Serie.create("1"), this.selecionePor(1)));

      // Escola3
      this.listaMaterialEscolar.add(ListaMaterial.create(escola3, Serie.create("2"), this.selecionePor(2)));
      this.listaMaterialEscolar.add(ListaMaterial.create(escola3, Serie.create("1"), this.selecionePor(1)));
      this.listaMaterialEscolar.add(ListaMaterial.create(escola3, Serie.create("4"), this.selecionePor(4)));
   }

   private void initProdutos() {
      final List<DescricaoMaterialEscolarImpl> listaItens = this.itens.stream().collect(Collectors.toList());
      this.produtos = new HashSet<>();

      for (int i = 0; i < listaItens.size(); i++) {
         final ProdutoImpl produto = ProdutoImpl.create(listaItens.get(i), BigDecimal.valueOf(i + 1), Integer.valueOf(i + 1));
         this.produtos.add(produto);
      }

      this.initEstabelecimentos();
   }

   private void initEstabelecimentos() {

      final List<ProdutoImpl> todos = this.produtos.stream().collect(Collectors.toList());

      // Estabelecimento1
      final ListaProduto produtosEstabelecimento1 = new ListaProduto(todos.get(0), todos.get(1));
      final Estabelecimento estabelecimento1 = Estabelecimento.create("Estabelecimento1", produtosEstabelecimento1);

      // Estabelecimento2
      final ListaProduto produtosEstabelecimento2 = new ListaProduto(todos);
      final Estabelecimento estabelecimento2 = Estabelecimento.create("Estabelecimento2", produtosEstabelecimento2);

      // Estabelecimento3
      final ListaProduto produtosEstabelecimento3 = new ListaProduto(todos.get(9), todos.get(8), todos.get(7), todos.get(6), todos.get(5), todos.get(4));
      final Estabelecimento estabelecimento3 = Estabelecimento.create("Estabelecimento3", produtosEstabelecimento3);

      // Estabelecimento4
      final ListaProduto produtosEstabelecimento4 = new ListaProduto(todos.get(0), todos.get(2), todos.get(4), todos.get(6), todos.get(8));
      final Estabelecimento estabelecimento4 = Estabelecimento.create("Estabelecimento4", produtosEstabelecimento4);

      // Estabelecimento5
      final ListaProduto produtosEstabelecimento5 = new ListaProduto(todos.get(1), todos.get(3), todos.get(5), todos.get(7), todos.get(9));
      final Estabelecimento estabelecimento5 = Estabelecimento.create("Estabelecimento5", produtosEstabelecimento5);

      this.estabelecimentos = new ListaEstabelecimento(Arrays.asList(estabelecimento1, estabelecimento2, estabelecimento3, estabelecimento4, estabelecimento5));
   }

   private List<ItemImpl> selecionePor(final int serie) {
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
   public DescricaoMaterialEscolarRepository meDaUmBancoDeMaterial() {
      return new DescricaoMaterialEscolarRepository() {

         @Override
         public DescricaoMaterialEscolar salvaSaPorra(final DescricaoMaterialEscolarImpl descricaoMaterialEscolar) {
            LocalDb.this.itens.add(descricaoMaterialEscolar);
            return descricaoMaterialEscolar;
         }

         @Override
         public Collection<DescricaoMaterialEscolarImpl> meDaTudo() {
            return LocalDb.this.itens;
         }

         @Override
         public DescricaoMaterialEscolar selecionarPor(final DescricaoMaterialEscolarImpl materialEscolar) {
            if (!LocalDb.this.itens.contains(materialEscolar)) {
               LocalDb.this.itens.add(materialEscolar);
            }

            return LocalDb.this.itens.stream().filter(materialEscolar::equals).findAny()
                  .orElseThrow(() -> new IllegalError("Não foi possível encontrar o material escolar: [" + materialEscolar + "]"));
         }
      };
   }

   @Override
   public EscolaRepository meDaUmBancoDeEscola() {
      return new EscolaRepository() {

         @Override
         public Escola salvaSaPorra(final Escola escola) {
            LocalDb.this.escolas.add(escola);
            return escola;
         }

         @Override
         public Collection<Escola> escolas() {
            return LocalDb.this.escolas;
         }
      };
   }

   @Override
   public ListaMaterialRepository meDaUmBancoDeListaMaterial() {
      return new ListaMaterialRepository() {

         @Override
         public Collection<ListaMaterial> materiais() {
            return LocalDb.this.listaMaterialEscolar;
         }

         @Override
         public ListaMaterial salvaSaPorra(final ListaMaterial listaMaterial) {
            LocalDb.this.listaMaterialEscolar.add(listaMaterial);
            return listaMaterial;
         }

      };
   }

   @Override
   public EstabelecimentoRepository meDaUmBancoDeEstabelecimentos() {
      return new EstabelecimentoRepository() {

         @Override
         public Estabelecimento salvaSaPorra(final Estabelecimento estabelecimento) {
            LocalDb.this.estabelecimentos.add(estabelecimento);
            return estabelecimento;
         }

         @Override
         public ListaEstabelecimento estabelecimentos() {
            return LocalDb.this.estabelecimentos;
         }

         @Override
         public Optional<Estabelecimento> selecionePor(final Estabelecimento estabelecimento) {
            return LocalDb.this.estabelecimentos.meDaUm(estabelecimento);

         }
      };
   }

}
