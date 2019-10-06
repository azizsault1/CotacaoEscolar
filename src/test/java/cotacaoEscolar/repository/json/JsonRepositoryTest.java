package cotacaoEscolar.repository.json;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.Test;

import cotacaoEscolar.repository.DescricaoMaterialEscolarRepository;
import cotacaoEscolar.repository.EscolaRepository;
import cotacaoEscolar.repository.EstabelecimentoRepository;
import cotacaoEscolar.repository.ListaMaterialRepository;

public class JsonRepositoryTest {

   private final String dbTest = "src/test/resources/dbfiles/";
   private final JsonRepository tamburete;

   public JsonRepositoryTest() throws IOException, GeneralSecurityException {
      this.tamburete = new JsonRepository(this.dbTest);
   }

   @Test
   public void testandoCriarRepEscola() {
      final EscolaRepository resultado = this.tamburete.meDaUmBancoDeEscola();
      assertNotNull(resultado);
   }

   @Test
   public void testandoCriarRepEstabelecimento() {
      final EstabelecimentoRepository resultado = this.tamburete.meDaUmBancoDeEstabelecimentos();
      assertNotNull(resultado);
   }

   @Test
   public void testandoCriarRepListaMaterial() {
      final ListaMaterialRepository resultado = this.tamburete.meDaUmBancoDeListaMaterial();
      assertNotNull(resultado);
   }

   @Test
   public void testandoCriarRepDeDescricaoMaterial() {
      final DescricaoMaterialEscolarRepository resultado = this.tamburete.meDaUmBancoDeMaterial();
      assertNotNull(resultado);
   }

}
