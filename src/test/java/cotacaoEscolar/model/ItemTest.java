package cotacaoEscolar.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cotacaoEscolar.model.listas.ListaItem;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ItemTest {

        @Test
        public void toJson() throws JsonProcessingException {
            final DescricaoMaterialEscolar lapisDesc = new DescricaoMaterialEscolar("Lapis 123");
            final Item lapis = new Item(lapisDesc, 5);

            final String result = new ObjectMapper().writeValueAsString(lapis);
            String expected = "{\"descricao\":\"Lapis 123\",\"quantidade\":5}";
            Assert.assertEquals(expected, result);
        }

        @Test
        public void fromJson() throws IOException {
            String json = "{\"descricao\":\"Lapis 123\",\"quantidade\":5}";

            final Item item = new ObjectMapper().readValue(json, Item.class);
            Assert.assertEquals("Lapis 123", item.getDescricao());
            Assert.assertEquals(5, item.getQuantidade());
        }

}