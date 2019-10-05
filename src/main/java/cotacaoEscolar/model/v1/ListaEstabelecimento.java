package cotacaoEscolar.model.v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cotacaoEscolar.model.ListaMaterial;

@JsonSerialize
public class ListaEstabelecimento {

   private final List<Estabelecimento> estabelecimentos;

   public ListaEstabelecimento() {
      this.estabelecimentos = new ArrayList<>();
   }

   public ListaEstabelecimento(final List<Estabelecimento> estabelecimentos) {
      this.estabelecimentos = new ArrayList<>(estabelecimentos);
   }

   public void addAll(final List<Estabelecimento> estabelecimentos) {
      this.estabelecimentos.addAll(estabelecimentos);
   }

   public List<Estabelecimento> getEstabelecimentos() {
      return this.estabelecimentos;
   }

   public ResultadoCotacao cotar(final ListaMaterial lista) {
      final List<ResultadoCotacaoEstabelecimento> resultados = new ArrayList<>();

      this.estabelecimentos.forEach(estabelecimento -> resultados.add(estabelecimento.cotar(lista)));

      Collections.sort(resultados);
      return new ResultadoCotacao(resultados);
   }

   public void add(final Estabelecimento estabelecimento) {
      this.estabelecimentos.add(estabelecimento);
   }

   public Optional<Estabelecimento> meDaUm(final Estabelecimento estabelecimento) {
      //@formatter:off
      return this.estabelecimentos.stream()
         .filter(encontrado -> encontrado.equals(estabelecimento))
         .findAny();

      //@formatter:off
   }

}
