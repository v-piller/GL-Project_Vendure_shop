import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductIDQueryTest {
    @Test
    public void testProductIDQuery() {
        String id = "2";
        String response =
        """
        {
          "data": {
            "product": {
              "name": "Tablet",
              "variants": [
                {
                  "priceWithTax": 39480
                },
                {
                  "priceWithTax": 53400
                }
              ]
            }
          }
        }
        """;
        ProductIDQuery productIDQuery = new ProductIDQuery(id);

        assertTrue(productIDQuery.getQuery().contains("2"));

        Product result = productIDQuery.parseResponse(response);

        assertEquals("Tablet", result.getName());
        assertEquals(39480, result.getPrice());
        }
}
