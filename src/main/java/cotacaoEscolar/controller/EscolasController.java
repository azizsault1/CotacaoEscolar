package cotacaoEscolar.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Escolas;
import cotacaoEscolar.model.v1.EscolaReal;
import cotacaoEscolar.model.v1.helpers.EscolaAutoSave;
import cotacaoEscolar.service.ServicoEscola;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "escolas")
@RestController
@RequestMapping("v1")
public class EscolasController implements Escolas {

   private final ServicoEscola servico;

   @Autowired
   public EscolasController(final ServicoEscola servico) {
      this.servico = servico;
   }

   @Override
   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Salva uma escola.")
   @PostMapping(value = "escola", produces = "application/json", consumes = "application/json")
   public Escola salvar(@RequestBody final Escola escola) throws FoiNao {
      final EscolaAutoSave escolaSalva = EscolaReal.create(this.servico, escola.getNome());
      this.servico.salvar(escola);
      return escolaSalva;
   }

   @Override
   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Busca as escolas cadastradas")
   @GetMapping(value = "escolas", produces = "application/json")
   public Collection<Escola> todas() {
      return this.servico.todas();
   }

}
