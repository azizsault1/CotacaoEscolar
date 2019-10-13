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
import cotacaoEscolar.model.v1.Estabelecimento;
import cotacaoEscolar.model.v1.Produto;
import cotacaoEscolar.service.ServicoEstabelecimento;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Estabelecimento")
@RestController
@RequestMapping("v1")
public class EstabelecimentoController {

   private final ServicoEstabelecimento servico;

   @Autowired
   public EstabelecimentoController(final ServicoEstabelecimento servico) {
      this.servico = servico;
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Salva um estabelecimento.")
   @PostMapping(value = "estabelcimento", produces = "application/json", consumes = "application/json")
   public Estabelecimento estabelecimento(@RequestBody final String nome) throws FoiNao {
      final Estabelecimento estabelecimento = Estabelecimento.create(nome);
      this.servico.salvar(estabelecimento);
      return estabelecimento;
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Adiciona uma lista de produtos em um estabelecimento.")
   @PostMapping(value = "estabelcimento/{nome}/produtos", produces = "application/json", consumes = "application/json")
   public Estabelecimento adicionarProduto(@PathVariable final String nome, @RequestBody final List<Produto> produtos) throws FoiNao {
      final Estabelecimento vouProcurarEstabelecimento = Estabelecimento.create(nome);
      final Optional<Estabelecimento> estabelecimentoEncontradoOpt = this.servico.selecionePor(vouProcurarEstabelecimento);

      if (!estabelecimentoEncontradoOpt.isPresent()) {
         throw new IllegalError("Tah moscando pivete? Que estabelecimento eh esse? [" + nome + "]");
      }

      final Estabelecimento estabelecimento = estabelecimentoEncontradoOpt.get();
      produtos.forEach(produto -> estabelecimento.adicioneMaisUmProdutoAe(produto));
      this.servico.salvar(estabelecimento);
      return estabelecimento;

   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Adiciona um produto no estabelecimento.")
   @PostMapping(value = "estabelcimento/{nome}/produto", produces = "application/json", consumes = "application/json")
   public Estabelecimento adicionarProduto(@PathVariable final String nome, @RequestBody final Produto produto) throws FoiNao {
      final Estabelecimento vouProcurarEstabelecimento = Estabelecimento.create(nome);
      final Optional<Estabelecimento> estabelecimentoEncontradoOpt = this.servico.selecionePor(vouProcurarEstabelecimento);

      if (!estabelecimentoEncontradoOpt.isPresent()) {
         throw new IllegalError("Tah moscando pivete? Que estabelecimento eh esse? [" + nome + "]");
      }

      final Estabelecimento estabelecimento = estabelecimentoEncontradoOpt.get();

      estabelecimento.adicioneMaisUmProdutoAe(produto);
      this.servico.salvar(estabelecimento);
      return estabelecimento;

   }
}
