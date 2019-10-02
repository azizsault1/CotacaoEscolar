package cotacaoEscolar.repository.pojos;

import java.math.BigDecimal;

import cotacaoEscolar.model.Produto;
import io.jsondb.annotation.Document;
import io.jsondb.annotation.Id;

@Document(collection = "produtos", schemaVersion = "1.0")
public class ProdutoPojo implements ParserToModel<Produto> {

   @Id
   private DescricaoMaterialEscolarPojo materialEscolar;
   private double valor;
   private Integer quantidadeEstoque;

   public ProdutoPojo() {
   }

   public ProdutoPojo(final Produto produto) {
      this.materialEscolar = new DescricaoMaterialEscolarPojo(produto.getDescricao());
      this.valor = produto.getValor().doubleValue();
      this.quantidadeEstoque = produto.getQuantidade();
   }

   public DescricaoMaterialEscolarPojo getMaterialEscolar() {
      return this.materialEscolar;
   }

   public void setMaterialEscolar(final DescricaoMaterialEscolarPojo materialEscolar) {
      this.materialEscolar = materialEscolar;
   }

   public double getValor() {
      return this.valor;
   }

   public void setValor(final double valor) {
      this.valor = valor;
   }

   public Integer getQuantidadeEstoque() {
      return this.quantidadeEstoque;
   }

   public void setQuantidadeEstoque(final Integer quantidadeEstoque) {
      this.quantidadeEstoque = quantidadeEstoque;
   }

   @Override
   public Produto toModel() {
      return Produto.create(this.materialEscolar.getDescricao(), BigDecimal.valueOf(this.valor), this.quantidadeEstoque);
   }

}
