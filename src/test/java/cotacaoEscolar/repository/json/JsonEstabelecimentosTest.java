package cotacaoEscolar.repository.json;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.Test;

import cotacaoEscolar.model.v1.Estabelecimento;
import cotacaoEscolar.model.v1.ListaEstabelecimento;
import cotacaoEscolar.model.v1.ListaProduto;
import cotacaoEscolar.model.v1.Produto;

public class JsonEstabelecimentosTest {

   private final String dbTest = "src/test/resources/dbfiles/";
   private final JsonEstabelecimentos tamburete;

   @Before
   public void antesDeTudo() throws IOException {
      FileUtils.deleteDirectory(new File(this.dbTest));
   }

   public JsonEstabelecimentosTest() throws IOException, GeneralSecurityException {
      final JsonRepository banco = new JsonRepository(this.dbTest);
      this.tamburete = new JsonEstabelecimentos(banco);
   }

   @Test
   public void testeSalvar() {
      final String materialEscolar1 = "material1";
      final BigDecimal valor1 = BigDecimal.valueOf(5.00);
      final Integer quantidade1 = 10;
      final String materialEscolar2 = "material2";
      final BigDecimal valor2 = BigDecimal.valueOf(20, 00);
      final Integer quantidade2 = 2;

      final Produto produto1 = Produto.create(materialEscolar1, valor1, quantidade1);
      final Produto produto2 = Produto.create(materialEscolar2, valor2, quantidade2);
      final List<Produto> produtos = Arrays.asList(produto1, produto2);
      final ListaProduto listaProduto = new ListaProduto(produtos);
      final Estabelecimento estabelecimento = Estabelecimento.create("Estabelecimento1", listaProduto);
      this.tamburete.salvaSaPorra(estabelecimento);

      final ListaEstabelecimento result = this.tamburete.estabelecimentos();
      assertNotNull(result);
   }

   @Test
   public void testeBuscarEstabelecimentoExistente() {
      final String materialEscolar1 = "material1";
      final BigDecimal valor1 = BigDecimal.valueOf(5.00);
      final Integer quantidade1 = 10;
      final String materialEscolar2 = "material2";
      final BigDecimal valor2 = BigDecimal.valueOf(20, 00);
      final Integer quantidade2 = 2;

      final Produto produto1 = Produto.create(materialEscolar1, valor1, quantidade1);
      final Produto produto2 = Produto.create(materialEscolar2, valor2, quantidade2);
      final List<Produto> produtos = Arrays.asList(produto1, produto2);
      final ListaProduto listaProduto = new ListaProduto(produtos);
      final Estabelecimento estabelecimento = Estabelecimento.create("Estabelecimento1", listaProduto);
      this.tamburete.salvaSaPorra(estabelecimento);

      final Optional<Estabelecimento> result = this.tamburete.selecionePor(estabelecimento);
      assertNotNull(result);
   }

   @Test
   public void testeBuscarEstabelecimentoInexistente() {
      final Estabelecimento estabelecimento = Estabelecimento.create("CantinaZezinho");
      this.tamburete.selecionePor(estabelecimento);
   }
}
