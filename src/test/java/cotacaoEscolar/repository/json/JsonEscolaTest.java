package cotacaoEscolar.repository.json;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collection;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.Test;

import cotacaoEscolar.model.Escola;

public class JsonEscolaTest {

   private final String dbTest = "src/test/resources/dbfiles/";
   private final JsonEscola tamburete;

   @Before
   public void antesDeTudo() throws IOException {
      FileUtils.deleteDirectory(new File(this.dbTest));
   }

   public JsonEscolaTest() throws IOException, GeneralSecurityException {
      final JsonRepository banco = new JsonRepository(this.dbTest);
      this.tamburete = new JsonEscola(banco);
   }

   @Test
   public void testeSalvar() {
      final Escola escola = new Escola("Escola1");
      this.tamburete.salvaSaPorra(escola);

      final Collection<Escola> result = this.tamburete.escolas();
      assertEquals(1, result.size());

   }

}
