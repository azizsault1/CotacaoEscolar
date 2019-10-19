package cotacaoEscolar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolarImpl;
import cotacaoEscolar.service.ServicoDescricaoMaterialEscolar;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Lista Material")
@RestController
@RequestMapping("v1")
public class DescricaoMaterialEscolarController {

   private final ServicoDescricaoMaterialEscolar servico;

   @Autowired
   public DescricaoMaterialEscolarController(final ServicoDescricaoMaterialEscolar servico) {
      this.servico = servico;
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Salva uma lista de material escolar.")
   @PostMapping(value = "descricaoMaterial", produces = "application/json", consumes = "application/json")
   public DescricaoMaterialEscolar descricao(@RequestBody final String descricao) throws FoiNao {
      final DescricaoMaterialEscolarImpl descricaoMaterial = DescricaoMaterialEscolarImpl.create(descricao);
      this.servico.salvar(descricaoMaterial);
      return descricaoMaterial;
   }

}
