import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ProductsQuery implements GraphQLQuery<List<Product>> {

    @Override
    public String getQuery() {
        return """
        {"query": "{ products { items { name variants { priceWithTax } } } }"}
        """;
    }

    @Override
    public List<Product> parseResponse(String responseBody) {
        List<Product> products = new ArrayList<>();

        JSONObject json = new JSONObject(responseBody);
        JSONArray items =
                json.getJSONObject("data").getJSONObject("products").getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            String name = item.getString("name");

            JSONArray variants = item.getJSONArray("variants");
            double price = variants.getJSONObject(0).getDouble("priceWithTax");

            products.add(new Product(name, price));
        }

        return products;
    }
}