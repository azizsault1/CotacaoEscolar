package cotacaoEscolar.model.listas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cotacaoEscolar.model.DescricaoMaterialEscolar;
import cotacaoEscolar.model.Item;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ListaItemTest {

    @Test
    public void toJson() throws JsonProcessingException {
        final DescricaoMaterialEscolar lapisDesc = new DescricaoMaterialEscolar("Lapis 123");
        final Item lapis = new Item(lapisDesc, 5);

        final DescricaoMaterialEscolar lapisDeCorDesc = new DescricaoMaterialEscolar("Lapis de cor");
        final Item lapisDeCor = new Item(lapisDeCorDesc, 30);

        final DescricaoMaterialEscolar classificadorDesc = new DescricaoMaterialEscolar("Classificador");
        final Item classificador = new Item(classificadorDesc, 2);

        final DescricaoMaterialEscolar cadernoDesc = new DescricaoMaterialEscolar("Caderno");
        final Item caderno = new Item(cadernoDesc, 1);

        final ListaItem lista = new ListaItem(lapis, lapisDeCor, classificador, caderno);
        final String result = new ObjectMapper().writeValueAsString(lista);
        String expected = "[{\"descricao\":\"Lapis 123\",\"quantidade\":5},{\"descricao\":\"Lapis de cor\"," +
                "\"quantidade\":30},{\"descricao\":\"Classificador\",\"quantidade\":2},{\"descricao\":\"Caderno\",\"quantidade\":1}]";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void fromJson() throws IOException {
        String json = "[{\"descricao\":\"Lapis 123\",\"quantidade\":5},{\"descricao\":\"Lapis de cor\"," +
                "\"quantidade\":30},{\"descricao\":\"Classificador\",\"quantidade\":2},{\"descricao\":\"Caderno\"," +
                "\"quantidade\":1}]";

        final ListaItem listaItem = new ObjectMapper().readValue(json, ListaItem.class);
        Assert.assertFalse(listaItem.isEmpty());
        final List<Item> itens = listaItem.getItens();
        Assert.assertEquals(4, itens.size());
        Item item1 = itens.get(0);
        Assert.assertEquals("Lapis 123", item1.getDescricao());
        Assert.assertEquals(5, item1.getQuantidade());
    }
}