package cotacaoEscolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotacaoEscolar.app.IllegalError;
import cotacaoEscolar.controller.dtos.SerieDto;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.v1.EscolaReal;
import cotacaoEscolar.model.v1.Item;
import cotacaoEscolar.model.v1.ListaEstabelecimento;
import cotacaoEscolar.model.v1.ListaMaterialReal;
import cotacaoEscolar.model.v1.ResultadoCotacao;
import cotacaoEscolar.model.v1.Serie;
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
   public ResultadoCotacao cotar(@RequestBody final ListaMaterialReal lista) {
      this.validate(lista);
      final ListaEstabelecimento estabelecimentos = this.servicoEstabelecimento.todos();
      return this.servicoCotacao.cotar(lista, estabelecimentos);
   }

   @CrossOrigin(origins = "*")
   @PostMapping(value = "escola", produces = "application/json", consumes = "application/json")
   public Escola salvar(@RequestBody final EscolaReal escola) {
      this.servicoEscola.salvar(escola);
      return escola;
   }

   @CrossOrigin(origins = "*")
   @PostMapping(value = "serie", produces = "application/json", consumes = "application/json")
   public Escola salvar(@RequestBody final SerieDto serieDto) {
      if (serieDto == null) {
         throw new IllegalError("Opppss... dados inválidos.");
      }
      final Escola escola = serieDto.getEscolaModel();
      final Serie serie = serieDto.getSerie();
      this.servicoListaMaterial.salvar(escola, serie);
      return escola;
   }

   private void validate(final ListaMaterialReal lista) {
      final Escola escola = lista.getEscola();
      final Serie serie = lista.getSerie();
      new SerieDto(escola.getNome(), serie.getSerie()).validate();

      final List<Item> itens = lista.getItens();
      if ((itens == null) || itens.isEmpty()) {
         throw new IllegalError("A lista de itens procurados nao pode ser vazia.");
      }
   }

}
