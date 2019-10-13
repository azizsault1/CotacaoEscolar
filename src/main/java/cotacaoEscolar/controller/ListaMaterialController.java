package cotacaoEscolar.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Escolas;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.model.v1.helpers.ListaMaterialAutoSave;
import cotacaoEscolar.service.ServicoListaMaterial;
import io.swagger.annotations.ApiOperation;

public class ListaMaterialController {

   private final Escolas escolas;
   private final ServicoListaMaterial servico;

   public ListaMaterialController(final Escolas escolas, final ServicoListaMaterial servico) {
      this.escolas = escolas;
      this.servico = servico;
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Adiciona uma Serie em uma escola e salva uma lista de material com os itens vazios.")
   @PostMapping(value = "serie", produces = "application/json", consumes = "application/json")
   public ListaMaterial salvar(@RequestBody final cotacaoEscolar.controller.dtos.Serie serieDto) throws FoiNao {
      if (serieDto == null) {
         throw new IllegalError("Opppss... dados inválidos.");
      }
      final Escola escola = serieDto.getEscolaModel();
      this.escolas.salvar(escola);
      final Serie serie = serieDto.getSerieModel();
      final ListaMaterialAutoSave listaSalva = this.servico.selecionePor(escola, serie);
      return listaSalva;
   }
}
