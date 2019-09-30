package cotacaoEscolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotacaoEscolar.app.IllegalError;
import cotacaoEscolar.controller.dtos.Serie;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.ResultadoCotacao;
import cotacaoEscolar.model.listas.ListaEstabelecimento;
import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.service.ServicoCotacao;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoEstabelecimento;
import cotacaoEscolar.service.ServicoListaMaterial;
import io.swagger.annotations.Api;

@Api(value = "Cotação")
@RestController
@RequestMapping("v1")
public class ControllerAlteracaoRest {

   private final ServicoCotacao servicoCotacao;
   private final ServicoEstabelecimento servicoEstabelecimento;
   private final ServicoEscola servicoEscola;
   private final ServicoListaMaterial servicoListaMaterial;

   @Autowired
   public ControllerAlteracaoRest(final ServicoCotacao servicoCotacao, final ServicoEstabelecimento servicoEstabelecimento, final ServicoEscola servicoEscola,
         final ServicoListaMaterial servicoListaMaterial) {
      this.servicoCotacao = servicoCotacao;
      this.servicoEstabelecimento = servicoEstabelecimento;
      this.servicoEscola = servicoEscola;
      this.servicoListaMaterial = servicoListaMaterial;
   }

   @CrossOrigin(origins = "*")
   @PostMapping(value = "cotar", produces = "application/json", consumes = "application/json")
   public ResultadoCotacao cotar(@RequestBody final ListaMaterial lista) {
      this.validate(lista);
      final ListaEstabelecimento estabelecimentos = this.servicoEstabelecimento.todos();
      return this.servicoCotacao.cotar(lista, estabelecimentos);
   }

   @CrossOrigin(origins = "*")
   @PostMapping(value = "escola", produces = "application/json", consumes = "application/json")
   public Escola salvar(@RequestBody final Escola escola) {
      this.servicoEscola.salvar(escola);
      return escola;
   }

   @CrossOrigin(origins = "*")
   @PostMapping(value = "serie", produces = "application/json", consumes = "application/json")
   public Escola salvar(@RequestBody final Serie serie) {
      if (serie == null) {
         throw new IllegalError("Opppss... dados inválidos.");
      }
      final Escola escola = serie.getEscolaModel();
      this.servicoListaMaterial.salvar(escola, serie.getSerie());
      return escola;
   }

   private void validate(final ListaMaterial lista) {
      final Escola escola = lista.getEscola();
      final String serie = lista.getSerie();
      new Serie(escola.getNome(), serie).validate();
      ;

      final List<Item> itens = lista.getItens();
      if ((itens == null) || itens.isEmpty()) {
         throw new IllegalError("A lista de itens procurados nao pode ser vazia.");
      }
   }

}
