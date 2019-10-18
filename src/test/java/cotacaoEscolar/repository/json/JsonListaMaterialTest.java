package cotacaoEscolar.repository.json;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Before;
import org.junit.Test;

import cotacaoEscolar.model.Escola;
import cotacaoEscolar.model.ListaMaterial;
import cotacaoEscolar.model.v1.ItemImpl;
import cotacaoEscolar.model.v1.Serie;

public class JsonListaMaterialTest {
   private final String dbTest = "src/test/resources/dbfiles/";
   private final JsonListaMaterial tamburete;

   public JsonListaMaterialTest() throws IOException, GeneralSecurityException {
      final JsonRepository banco = new JsonRepository(this.dbTest);
      this.tamburete = new JsonListaMaterial(banco);
   }

   @Before
   public void antesDeTudo() throws IOException {
      FileUtils.deleteDirectory(new File(this.dbTest));
   }

   @Test
   public void testSalvaSaPorra() {
      final Escola escola = Escola.create("Escola1");
      final Serie serie = Serie.create("1");
      final ItemImpl item1 = new ItemImpl("Item1", 10);
      final ItemImpl item2 = new ItemImpl("Item2", 20);

      final List<ItemImpl> itens = java.util.Arrays.asList(item1, item2);

      final ListaMaterial listaMaterial = ListaMaterial.create(escola, serie, itens);

      this.tamburete.salvaSaPorra(listaMaterial);
      final Collection<ListaMaterial> seraQueVoltou = this.tamburete.materiais();
      assertEquals(1, seraQueVoltou.size());
   }

}
