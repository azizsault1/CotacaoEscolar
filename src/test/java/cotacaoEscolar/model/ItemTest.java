package cotacaoEscolar.model;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemTest {

   @Test
   public void toJson() throws JsonProcessingException {
      final DescricaoMaterialEscolar lapisDesc = DescricaoMaterialEscolar.create("Lapis 123");
      final Item lapis = new Item(lapisDesc, 5);

      final String result = new ObjectMapper().writeValueAsString(lapis);
      final String expected = "{\"materialEscolar\":{\"descricao\":\"Lapis 123\"},\"quantidade\":5}";
      Assert.assertEquals(expected, result);
   }

   @Test
   public void fromJson() throws IOException {
      final String json = "{\"materialEscolar\":{\"descricao\":\"Lapis 123\"},\"quantidade\":5}";

      final Item item = new ObjectMapper().readValue(json, Item.class);
      Assert.assertEquals(DescricaoMaterialEscolar.create("Lapis 123"), item.getMaterialEscolar());
      Assert.assertEquals(5, item.getQuantidade());
   }

}