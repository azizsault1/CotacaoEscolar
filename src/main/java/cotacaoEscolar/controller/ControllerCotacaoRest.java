package cotacaoEscolar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
      final ListaEstabelecimento estabelecimentos = this.servicoEstabelecimento.todos();
      return this.servicoCotacao.cotar(lista, estabelecimentos);
   }

}
