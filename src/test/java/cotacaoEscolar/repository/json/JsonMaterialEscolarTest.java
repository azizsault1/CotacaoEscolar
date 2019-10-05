package cotacaoEscolar.repository.json;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.Test;

import cotacaoEscolar.app.exceptions.FoiNao;
import cotacaoEscolar.model.v1.DescricaoMaterialEscolar;

public class JsonMaterialEscolarTest {

   private static final String dbTest = "src/test/resources/dbfiles/";
   private final JsonMaterialEscolar tamburete;

   @Before
   public void antesDeTudo() throws IOException {
      FileUtils.deleteDirectory(new File(dbTest));
   }

   public JsonMaterialEscolarTest() throws IOException, GeneralSecurityException {
      final JsonRepository banco = new JsonRepository(dbTest);
      this.tamburete = new JsonMaterialEscolar(banco);
   }

   @Test
   public void salvaUmAe() throws FoiNao {
      final DescricaoMaterialEscolar descricaoMaterialEscolar = DescricaoMaterialEscolar.create("Descricao1");

      this.tamburete.salvaSaPorra(descricaoMaterialEscolar);
      final List<DescricaoMaterialEscolar> veioIsso = this.tamburete.meDaTudo();

      assertEquals(1, veioIsso.size());
   }

   @Test
   public void agoraPegaUmAe() throws FoiNao {
      final DescricaoMaterialEscolar descricaoMaterialEscolar = DescricaoMaterialEscolar.create("Descricao1");

      this.tamburete.salvaSaPorra(descricaoMaterialEscolar);
      final DescricaoMaterialEscolar veioIsso = this.tamburete.selecionarPor(descricaoMaterialEscolar);

      assertEquals("Descricao1", veioIsso.getDescricao());
   }

}
