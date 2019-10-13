package cotacaoEscolar.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Estabelecimentos;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.Item;
import cotacaoEscolar.model.v1.ListaEstabelecimento;
import cotacaoEscolar.model.v1.ResultadoCotacao;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.repository.EstabelecimentoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Estabelecimentos")
@RestController
@RequestMapping("v1")
public class EstabelecimentosController implements Estabelecimentos {

   private final EstabelecimentoRepository repository;

   public EstabelecimentosController(final EstabelecimentoRepository repository) {
      this.repository = repository;
   }

   @Override
   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Realiza uma cotacao com a lista de material enviado.")
   @PostMapping(value = "cotar", produces = "application/json", consumes = "application/json")
   public ResultadoCotacao cotar(final ListaMaterial lista) {
      this.validate(lista);
      final ListaEstabelecimento estabelecimentos = this.repository.estabelecimentos();
      return estabelecimentos.cotar(lista);
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
