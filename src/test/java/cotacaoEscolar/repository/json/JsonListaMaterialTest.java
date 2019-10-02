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
import cotacaoEscolar.model.Item;
import cotacaoEscolar.model.listas.ListaMaterial;

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
      final Escola escola = new Escola("Escola1");
      final String serie = "1";
      final Item item1 = new Item("Item1", 10);
      final Item item2 = new Item("Item2", 20);

      final List<Item> itens = java.util.Arrays.asList(item1, item2);

      final ListaMaterial listaMaterial = new ListaMaterial(escola, serie, itens);

      this.tamburete.salvaSaPorra(listaMaterial);
      final Collection<ListaMaterial> seraQueVoltou = this.tamburete.materiais();
      assertEquals(1, seraQueVoltou.size());
   }

}
