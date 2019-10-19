package cotacaoEscolar.model.v1;

import java.math.BigDecimal;
import java.util.List;

import cotacaoEscolar.model.Item;

public interface ResultadoCotacaoEstabelecimento {

   String getNome();

   BigDecimal getTotal();

   List<Cotacao> getEncontrados();

   List<Item> getNaoEncontrados();

   String toReport();

}