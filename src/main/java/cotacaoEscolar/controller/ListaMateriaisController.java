package cotacaoEscolar.controller;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Escolas;
import cotacaoEscolar.model.ListaMateriaisEscolares;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.Serie;
import cotacaoEscolar.model.v1.helpers.ListaMaterialAutoSave;
import cotacaoEscolar.service.ServicoListaMaterial;
import io.swagger.annotations.ApiOperation;

public class ListaMateriaisController implements ListaMateriaisEscolares {

   private final ServicoListaMaterial servicoListaMaterial;
   private final Escolas escolas;

   public ListaMateriaisController(final Escolas escolas, final ServicoListaMaterial servicoListaMaterial) {
      this.servicoListaMaterial = servicoListaMaterial;
      this.escolas = escolas;
   }

   @Override
   public Collection<ListaMaterial> selecionePor(final Escola escola) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ListaMaterialAutoSave selecionePor(final Escola escola, final Serie serie) throws FoiNao {
      // TODO Auto-generated method stub
      return null;
   }

   @CrossOrigin(origins = "*")
   @ApiOperation(value = "Busca a lista de materiais de uma escola.")
   @GetMapping(value = "escola/{nomeEscola}", produces = MediaType.APPLICATION_JSON_VALUE)
   public Collection<ListaMaterial> todas(@PathVariable("nomeEscola") final String nomeEscola) throws IllegalError {
      return this.servicoListaMaterial.selecionePor(this.getEscola(nomeEscola));
   }

}
