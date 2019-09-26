package cotacaoEscolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotacaoEscolar.app.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.ResultadoCotacao;
import cotacaoEscolar.model.listas.ListaEstabelecimento;
import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.service.ServicoCotacao;
import cotacaoEscolar.service.ServicoEstabelecimento;
import io.swagger.annotations.Api;

@Api(value = "Cotação")
@RestController
@RequestMapping("v1")
public class ControllerCotacaoRest {

   private final ServicoCotacao servicoCotacao;
   private final ServicoEstabelecimento servicoEstabelecimento;

   @Autowired
   public ControllerCotacaoRest(final ServicoCotacao servicoCotacao, final ServicoEstabelecimento servicoEstabelecimento) {
      this.servicoCotacao = servicoCotacao;
      this.servicoEstabelecimento = servicoEstabelecimento;
   }

   @CrossOrigin(origins = "*")
   @PostMapping(value = "escolas", produces = "application/json", consumes = "application/json")
   public ResultadoCotacao cotar(@RequestBody final ListaMaterial lista) {
      this.validate(lista);
      final ListaEstabelecimento estabelecimentos = this.servicoEstabelecimento.todos();
      return this.servicoCotacao.cotar(lista, estabelecimentos);
   }

   private void validate(final ListaMaterial lista) {
      final Escola escola = lista.getEscola();
      if ((escola == null) || !escola.validate()) {
         throw new IllegalError("Escola invalida");
      }

      final Integer serie = lista.getSerie();
      if ((serie == null) || (serie == 0)) {
         throw new IllegalError("Serie invalida");
      }

      final List<Item> itens = lista.getItens();
      if ((itens == null) || itens.isEmpty()) {
         throw new IllegalError("A lista de itens procurados nao pode ser vazia.");
      }
   }

}
