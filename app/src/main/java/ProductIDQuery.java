import org.json.JSONObject;

import java.awt.*;

public class ProductIDQuery implements GraphQLQuery<Product> {

  private String id;

  public ProductIDQuery(String id) {
    this.id = id;
  }

  @Override
  public String getQuery() {
    return "{ product(id: \"" + id + "\") { name variants { priceWithTax } } }";
  }

  @Override
  public Product parseResponse(String responseBody) {
    System.out.println(responseBody); // debug
    JSONObject json = new JSONObject(responseBody);
    JSONObject product = json.getJSONObject("data").getJSONObject("product");

    String name = product.getString("name");
    double price = product.getJSONArray("variants").getJSONObject(0).getDouble("priceWithTax");

    return new Product(name, price);
  }
}
