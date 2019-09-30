package cotacaoEscolar.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotacaoEscolar.app.IllegalError;
import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.service.ServicoDescricaoMaterialEscolar;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoListaMaterial;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Material Escolar")
@RestController
@RequestMapping("v1")
public class ControllerBuscaRest {

   private final ServicoEscola servicoEscola;
   private final ServicoListaMaterial servicoListaMaterial;
   private final ServicoDescricaoMaterialEscolar servicoItem;

   @Autowired
   public ControllerBuscaRest(final ServicoEscola servicoEscola, final ServicoDescricaoMaterialEscolar servicoDescricaoMaterialEscolar,
         final ServicoListaMaterial servicoListaMaterial) {
      this.servicoEscola = servicoEscola;
      this.servicoItem = servicoDescricaoMaterialEscolar;
      this.servicoListaMaterial = servicoListaMaterial;
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Busca as escolas cadastradas")
   @GetMapping(value = "escolas", produces = "application/json")
   public Collection<Escola> todasEscolas() {
      return this.servicoEscola.todas();
   }

   @CrossOrigin(origins = "*")
   @GetMapping(value = "escola/{nomeEscola}", produces = "application/json")
   public Collection<ListaMaterial> ListaDeMateriaisEscolaresDa(@PathVariable("nomeEscola") final String nomeEscola) throws IllegalError {
      final Escola escolaEncontrada = this.servicoEscola.buscar(nomeEscola);
      return this.servicoListaMaterial.selecionePor(escolaEncontrada);
   }

   @CrossOrigin(origins = "*")
   @GetMapping(value = "series/{nomeEscola}", produces = "application/json")
   public Collection<String> SeriesDa(@PathVariable("nomeEscola") final String nomeEscola) {
      final Escola escolaEncontrada = this.servicoEscola.buscar(nomeEscola);
      return this.servicoListaMaterial.selecioneSeriesPor(escolaEncontrada);
   }

   @CrossOrigin(origins = "*")
   @GetMapping(value = "itens/{nomeEscola}/{serie}", produces = "application/json")
   public List<Item> itensDaListaDa(@PathVariable("nomeEscola") final String nomeEscola, @PathVariable("serie") final String serie) {
      final Escola escolaEncontrada = this.servicoEscola.buscar(nomeEscola);
      return this.servicoListaMaterial.selecionePor(escolaEncontrada, serie).getItens();
   }

   @CrossOrigin(origins = "*")
   @GetMapping(value = "materialEscolar/{nomeEscola}/{serie}", produces = "application/json")
   public ListaMaterial listaDeMateriais(@PathVariable("nomeEscola") final String nomeEscola, @PathVariable("serie") final String serie) {
      final Escola escolaEncontrada = this.servicoEscola.buscar(nomeEscola);
      return this.servicoListaMaterial.selecionePor(escolaEncontrada, serie);
   }

   @CrossOrigin(origins = "*")
   @GetMapping(value = "descricoes")
   public Collection<DescricaoMaterialEscolar> todasDescricoes() {
      return this.servicoItem.todasDescricoes();
   }

}
