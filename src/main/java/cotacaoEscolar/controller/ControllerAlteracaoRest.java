package cotacaoEscolar.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolar;
import cotacaoEscolar.model.v1.Estabelecimento;
import cotacaoEscolar.model.v1.Item;
import cotacaoEscolar.model.v1.ListaEstabelecimento;
import cotacaoEscolar.model.v1.ListaMaterialReal;
import cotacaoEscolar.model.v1.Produto;
import cotacaoEscolar.model.v1.ResultadoCotacao;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.service.ServicoCotacao;
import cotacaoEscolar.service.ServicoDescricaoMaterialEscolar;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoEstabelecimento;
import cotacaoEscolar.service.ServicoListaMaterial;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Cotação")
@RestController
@RequestMapping("v1")
public class ControllerAlteracaoRest {

   private final ServicoCotacao servicoCotacao;
   private final ServicoEstabelecimento servicoEstabelecimento;
   private final ServicoEscola servicoEscola;
   private final ServicoListaMaterial servicoListaMaterial;
   private final ServicoDescricaoMaterialEscolar servicoDescricaoMaterialEscolar;

   @Autowired
   public ControllerAlteracaoRest(final ServicoCotacao servicoCotacao, final ServicoEstabelecimento servicoEstabelecimento, final ServicoEscola servicoEscola,
         final ServicoListaMaterial servicoListaMaterial, final ServicoDescricaoMaterialEscolar descricaoMaterialEscolar) {
      this.servicoDescricaoMaterialEscolar = descricaoMaterialEscolar;
      this.servicoCotacao = servicoCotacao;
      this.servicoEstabelecimento = servicoEstabelecimento;
      this.servicoEscola = servicoEscola;
      this.servicoListaMaterial = servicoListaMaterial;
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Realiza uma cotacao com a lista de material enviado.")
   @PostMapping(value = "cotar", produces = "application/json", consumes = "application/json")
   public ResultadoCotacao cotar(@RequestBody final ListaMaterialReal lista) {
      this.validate(lista);
      final ListaEstabelecimento estabelecimentos = this.servicoEstabelecimento.todos();
      return this.servicoCotacao.cotar(lista, estabelecimentos);
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Salva uma escola.")
   @PostMapping(value = "escola", produces = "application/json", consumes = "application/json")
   public Escola escola(@RequestBody final Escola escola) throws FoiNao {
      this.servicoEscola.salvar(escola);
      return escola;
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Adiciona uma Serie em uma escola e salva uma lista de material com os itens vazios.")
   @PostMapping(value = "serie", produces = "application/json", consumes = "application/json")
   public Escola salvar(@RequestBody final cotacaoEscolar.controller.dtos.Serie serieDto) throws FoiNao {
      if (serieDto == null) {
         throw new IllegalError("Opppss... dados inválidos.");
      }
      final Escola escola = serieDto.getEscolaModel();
      final Serie serie = serieDto.getSerie();
      this.servicoListaMaterial.salvar(escola, serie);
      return escola;
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Salva uma lista de material escolar.")
   @PostMapping(value = "descricaoMaterial", produces = "application/json", consumes = "application/json")
   public DescricaoMaterialEscolar descricao(@RequestBody final String descricao) throws FoiNao {
      final DescricaoMaterialEscolar descricaoMaterial = DescricaoMaterialEscolar.create(descricao);
      this.servicoDescricaoMaterialEscolar.salvar(descricaoMaterial);
      return descricaoMaterial;
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Salva um estabelecimento.")
   @PostMapping(value = "estabelcimento", produces = "application/json", consumes = "application/json")
   public Estabelecimento estabelecimento(@RequestBody final String nome) throws FoiNao {
      final Estabelecimento estabelecimento = Estabelecimento.create(nome);
      this.servicoEstabelecimento.salvar(estabelecimento);
      return estabelecimento;
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Adiciona uma lista de produtos em um estabelecimento.")
   @PostMapping(value = "estabelcimento/{nome}/produtos", produces = "application/json", consumes = "application/json")
   public Estabelecimento adicionarProduto(@PathVariable final String nome, @RequestBody final List<Produto> produtos) throws FoiNao {
      final Estabelecimento vouProcurarEstabelecimento = Estabelecimento.create(nome);
      final Optional<Estabelecimento> estabelecimentoEncontradoOpt = this.servicoEstabelecimento.selecionePor(vouProcurarEstabelecimento);

      if (!estabelecimentoEncontradoOpt.isPresent()) {
         throw new IllegalError("Tah moscando pivete? Que estabelecimento eh esse? [" + nome + "]");
      }

      final Estabelecimento estabelecimento = estabelecimentoEncontradoOpt.get();
      produtos.forEach(produto -> estabelecimento.adicioneMaisUmProdutoAe(produto));
      this.servicoEstabelecimento.salvar(estabelecimento);
      return estabelecimento;

   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Adiciona um produto no estabelecimento.")
   @PostMapping(value = "estabelcimento/{nome}/produto", produces = "application/json", consumes = "application/json")
   public Estabelecimento adicionarProduto(@PathVariable final String nome, @RequestBody final Produto produto) throws FoiNao {
      final Estabelecimento vouProcurarEstabelecimento = Estabelecimento.create(nome);
      final Optional<Estabelecimento> estabelecimentoEncontradoOpt = this.servicoEstabelecimento.selecionePor(vouProcurarEstabelecimento);

      if (!estabelecimentoEncontradoOpt.isPresent()) {
         throw new IllegalError("Tah moscando pivete? Que estabelecimento eh esse? [" + nome + "]");
      }

      final Estabelecimento estabelecimento = estabelecimentoEncontradoOpt.get();

      estabelecimento.adicioneMaisUmProdutoAe(produto);
      this.servicoEstabelecimento.salvar(estabelecimento);
      return estabelecimento;

   }

   private void validate(final cotacaoEscolar.model.ListaMaterial lista) {
      final Escola escola = lista.getEscola();
      final Serie serie = lista.getSerie();
      new cotacaoEscolar.controller.dtos.Serie(escola.getNome(), serie.getSerie()).validate();

      final List<Item> itens = lista.getItens();
      if ((itens == null) || itens.isEmpty()) {
         throw new IllegalError("A lista de itens procurados nao pode ser vazia.");
      }
   }

}
