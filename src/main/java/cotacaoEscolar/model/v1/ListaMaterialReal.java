package cotacaoEscolar.model.v1;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.app.exceptions.IllegalError;
import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.repository.ListaMaterialRepository;

public class ListaMaterialReal implements ListaMaterial, Serializable {
   private static final long serialVersionUID = 1L;
   private final Escola escola;
   private final Serie serie;
   private final List<Item> itens;

   private final transient ListaMaterialRepository repository;

   private ListaMaterialReal(final ListaMaterialRepository repository, final Escola escola, final Serie serie, final List<Item> itens) {
      this.escola = escola;
      this.serie = serie;
      this.itens = itens;
      this.repository = repository;
   }

   @Override
   public Escola getEscola() {
      return this.escola;
   }

   @Override
   public Serie getSerie() {
      return this.serie;
   }

   @Override
   @JsonIgnore
   public boolean pertenceA(final Escola escola) {
      return this.escola.equals(escola);
   }

   @Override
   public List<Item> getItens() {
      return this.itens;
   }

   @Override
   @JsonIgnore
   public boolean pertenceASerie(final Serie serie) {
      return this.serie.equals(serie);
   }

   @Override
   public void addItem(final Item item) {
      this.itens.add(item);
   }

   @Override
   public boolean primeiraLista() {
      return false;
   }

   @Override
   public ListaMaterial salvar() throws FoiNao {
      this.repository.salvaSaPorra(this);
      return this;
   }

   @Override
   public boolean souNova() {
      return false;
   }

   public static ListaMaterial create(final ListaMaterialRepository repository, final Escola escola, final Serie serie) {
      validate(repository, escola, serie);
      return new ListaMaterialReal(repository, escola, serie, Collections.emptyList());
   }

   public static ListaMaterial create(final ListaMaterialRepository repository, final Escola escola, final Serie serie, final List<Item> itens) {
      validate(repository, escola, serie);
      if (itens == null) {
         throw new IllegalError("Chame o método create(repository, escola, serie)");
      }
      return new ListaMaterialReal(repository, escola, serie, Collections.emptyList());
   }

   private static void validate(final ListaMaterialRepository repository, final Escola escola, final Serie serie) {
      if (repository == null) {
         throw new IllegalError(
               "Opps... se você está chamando esse método é porque mais na frente você quer salvar esse objeto, então eu preciso do banco, se não use a Implementação PrimeiraLista.");
      }

      if (escola == null) {
         throw new IllegalError("Opps... essa escola não existe.");
      }

      if ((serie == null)) {
         throw new IllegalError("Opps... essa serie não existe.");
      }
   }

}
