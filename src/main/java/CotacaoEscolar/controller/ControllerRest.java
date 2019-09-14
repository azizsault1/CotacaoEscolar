package cotacaoEscolar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.ListaItem;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoItem;
import cotacaoEscolar.service.ServicoListaMaterial;

@RestController("/")
public class ControllerRest implements Controller {

   private final ServicoEscola servicoEscola;
   private final ServicoListaMaterial servicoListaMaterial;
   private final ServicoItem servicoItem;

   @Autowired
   public ControllerRest(final ServicoEscola servicoEscola, final ServicoItem servicoItem, final ServicoListaMaterial servicoListaMaterial) {
      this.servicoEscola = servicoEscola;
      this.servicoItem = servicoItem;
      this.servicoListaMaterial = servicoListaMaterial;
   }

   @Override
   @GetMapping(value = "escolas")
   public List<Escola> todasEscolas() {
      return this.servicoEscola.todas();
   }

   @Override
   public List<ListaMaterial> selecioneMaterialPor(final Escola escola) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public ListaItem selecioneMaterialPor(final Escola escola, final Integer serie) {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void remover(final Escola escola, final Integer serie, final Item item) {
      // TODO Auto-generated method stub

   }

   @Override
   public void adicionar(final Escola escola, final Integer serie, final Item item) {
      // TODO Auto-generated method stub

   }

   @Override
   @GetMapping(value = "descricoes")
   public List<DescricaoMaterialEscolar> todasDescricoes() {
      return this.servicoItem.todasDescricoes();
   }

}
