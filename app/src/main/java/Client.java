import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class Client {
  private String url;

  public Client(String url) {
    this.url = url;
  }

  public List<Product> getProducts() throws IOException, InterruptedException {
    List<Product> products = new ArrayList<>();
    // GraphQL query to get the list of products
    String query =
        """
            {"query": "{ products { items { name variants { priceWithTax } } } }"}
        """;

    // creates a client that is able to send HTTP requests
    HttpClient client = HttpClient.newHttpClient();

    // creates the request: like a letter (address, type of content, what, ...)
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(url)) // Where to send?
            .header("Content-Type", "application/json") // telling the server we're sending JSON
            .POST(
                HttpRequest.BodyPublishers.ofString(query)) // sending the query as the body (POST)
            .build();

    // Actually sends it to Vendure and waits for the response. response.body() will contain the
    // JSON with all the products.
    HttpResponse<String> response =
        client.send(
            request,
            HttpResponse.BodyHandlers
                .ofString()); // send the request and demand that the response is in String

    // then parse response.body() to get products
    JSONObject json = new JSONObject(response.body());
    JSONArray items =
        json.getJSONObject("data") // goes to what is contained in data
            .getJSONObject("products") // goes to what is contained in products
            .getJSONArray("items"); // gets the contents of items in array.

    // Goes through the array (items) and "extrcts" each item into the List (Product)
    for (int i = 0; i < items.length(); i++) {
      JSONObject item = items.getJSONObject(i);
      String name = item.getString("name");
      JSONArray variants = item.getJSONArray("variants"); // to get the price
      double price = variants.getJSONObject(0).getDouble("priceWithTax");
      products.add(new Product(name, price));
    }

    return products;
  }
}
