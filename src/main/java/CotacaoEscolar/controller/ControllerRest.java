package cotacaoEscolar.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.listas.ListaItem;
import cotacaoEscolar.model.listas.ListaMaterial;
import cotacaoEscolar.service.ServicoEscola;
import cotacaoEscolar.service.ServicoItem;
import cotacaoEscolar.service.ServicoListaMaterial;

@RestController("/")
public class ControllerRest {

   private final ServicoEscola servicoEscola;
   private final ServicoListaMaterial servicoListaMaterial;
   private final ServicoItem servicoItem;

   @Autowired
   public ControllerRest(final ServicoEscola servicoEscola, final ServicoItem servicoItem, final ServicoListaMaterial servicoListaMaterial) {
      this.servicoEscola = servicoEscola;
      this.servicoItem = servicoItem;
      this.servicoListaMaterial = servicoListaMaterial;
   }

   @GetMapping(value = "escolas", produces = "application/json")
   public Collection<Escola> todasEscolas() {
      return this.servicoEscola.todas();
   }

   @GetMapping(value = "escola/{nomeEscola}", produces = "application/json")
   public ResponseEntity<Object> selecioneMaterialPor(@PathVariable("nomeEscola") final String nomeEscola) {
      try {
         final Escola escolaEncontrada = this.servicoEscola.buscar(nomeEscola);
         final Collection<ListaMaterial> materiais = this.servicoListaMaterial.selecionePor(escolaEncontrada);
         return ResponseEntity.ok(materiais);
      } catch (final IllegalArgumentException e) {
         e.printStackTrace();
         return ResponseEntity.badRequest().body("Nao achei a escola: " + nomeEscola + ".");
      } catch (final Exception e) {
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro nao identificado.");
      }
   }

   @GetMapping(value = "series/{nomeEscola}", produces = "application/json")
   public ResponseEntity<Object> selecionarSerie(@PathVariable("nomeEscola") final String nomeEscola) {
      try {
         final Escola escolaEncontrada = this.servicoEscola.buscar(nomeEscola);
         final Collection<Integer> itens = this.servicoListaMaterial.selecioneSeriesPor(escolaEncontrada);
         return ResponseEntity.ok(itens);
      } catch (final IllegalArgumentException e) {
         e.printStackTrace();
         return ResponseEntity.badRequest().body("Nao achei a escola: " + nomeEscola + ".");
      } catch (final Exception e) {
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro nao identificado.");
      }
   }

   @GetMapping(value = "itens/{nomeEscola}/{serie}", produces = "application/json")
   public ResponseEntity<Object> selecioneMaterialPor(@PathVariable("nomeEscola") final String nomeEscola, @PathVariable("serie") final Integer serie) {
      try {
         final Escola escolaEncontrada = this.servicoEscola.buscar(nomeEscola);
         final ListaItem itens = this.servicoListaMaterial.selecionePor(escolaEncontrada, serie).getItens();
         return ResponseEntity.ok(itens);
      } catch (final IllegalArgumentException e) {
         e.printStackTrace();
         return ResponseEntity.badRequest().body("Nao achei a escola: " + nomeEscola + ".");
      } catch (final Exception e) {
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro nao identificado.");
      }
   }

   @GetMapping(value = "descricoes")
   public Collection<DescricaoMaterialEscolar> todasDescricoes() {
      return this.servicoItem.todasDescricoes();
   }

}
