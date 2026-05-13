import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class ProductsQueryTest {
  @Test
  void testProductsQuery() {
    String fakeResponse =
        """
        {
          "data": {
            "products": {
              "items": [
                {
                  "name": "Laptop",
                  "variants": [{ "priceWithTax": 129900 }]
                },
                {
                  "name": "Tablet",
                  "variants": [{ "priceWithTax": 39480 }]
                }
              ]
            }
          }
        }
        """;

    ProductsQuery productsQuery = new ProductsQuery();
    List<Product> result = productsQuery.parseResponse(fakeResponse);

    assertEquals(2, result.size());
    assertEquals("Laptop", result.get(0).getName());
    assertEquals(129900, result.get(0).getPrice());
    assertEquals("Tablet", result.get(1).getName());
  }
}
