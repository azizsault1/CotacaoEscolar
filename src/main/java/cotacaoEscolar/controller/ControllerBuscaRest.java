package cotacaoEscolar.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolar;
import cotacaoEscolar.model.v1.Item;
import cotacaoEscolar.model.v1.ListaEstabelecimento;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.service.ServicoDescricaoMaterialEscolar;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoEstabelecimento;
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
   private final ServicoEstabelecimento servicoEstabelecimentos;

   @Autowired
   public ControllerBuscaRest(final ServicoEscola servicoEscola, final ServicoDescricaoMaterialEscolar servicoDescricaoMaterialEscolar,
         , final ServicoEstabelecimento servicoEstabelecimentos) {
      this.servicoEscola = servicoEscola;
      this.servicoItem = servicoDescricaoMaterialEscolar;
      this.servicoListaMaterial = this.servicoListaMaterial;
      this.servicoEstabelecimentos = servicoEstabelecimentos;
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Busca as séries de uma escola.")
   @GetMapping(value = "series/{nomeEscola}", produces = MediaType.APPLICATION_JSON_VALUE)
   public Collection<String> SeriesDa(@PathVariable("nomeEscola") final String nomeEscola) {
      final Escola escolaEncontrada = this.getEscola(nomeEscola);
      final Collection<Serie> series = this.servicoListaMaterial.selecioneSeriesPor(escolaEncontrada);
      //@formatter:off
      return series
                  .stream().map(Serie::getSerie)
                  .collect(Collectors.toList());
      //@formatter:on
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Busca os itens da lista de material de uma escola e uma série.")
   @GetMapping(value = "itens/{nomeEscola}/{serie}", produces = MediaType.APPLICATION_JSON_VALUE)
   public List<Item> itensDaListaDa(@PathVariable("nomeEscola") final String nomeEscola, @PathVariable("serie") final String serie) throws FoiNao {
      final Escola escolaEncontrada = this.getEscola(nomeEscola);
      final Serie serieEncontrada = Serie.create(serie);
      return this.servicoListaMaterial.selecionePor(escolaEncontrada, serieEncontrada).getItens();
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Busca o material escolar de uma escola e uma série.")
   @GetMapping(value = "materialEscolar/{nomeEscola}/{serie}", produces = MediaType.APPLICATION_JSON_VALUE)
   public ListaMaterial listaDeMateriais(@PathVariable("nomeEscola") final String nomeEscola, @PathVariable("serie") final String serie) throws FoiNao {
      final Escola escolaEncontrada = this.getEscola(nomeEscola);
      final Serie serieEncontrada = Serie.create(serie);
      return this.servicoListaMaterial.selecionePor(escolaEncontrada, serieEncontrada);
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Busca todos os estabelecimentos cadastrados no sistema.")
   @GetMapping(value = "estabelecimentos", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
   public ListaEstabelecimento estabelecimentos() throws FoiNao {
      return this.servicoEstabelecimentos.todos();
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Busca a lista de descrições de materiais.")
   @GetMapping(value = "descricoes", produces = MediaType.APPLICATION_JSON_VALUE)
   public Collection<DescricaoMaterialEscolar> todasDescricoes() {
      return this.servicoItem.todasDescricoes();
   }

}
