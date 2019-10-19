package cotacaoEscolar.model;

import java.math.BigDecimal;

public interface Produto {

   String getDescricao();

   BigDecimal getValor();

   Integer getQuantidade();

   boolean equivale(final Item item);

}