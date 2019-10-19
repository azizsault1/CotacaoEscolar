package cotacaoEscolar.model;

public interface Item {

   DescricaoMaterialEscolar getMaterialEscolar();

   int getQuantidade();

   String toReport();

}