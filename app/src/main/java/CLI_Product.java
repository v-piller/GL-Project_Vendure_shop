import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "product", description = "Displays the chosen product")
public class CLI_Product implements Callable<Integer> {

  @Option(names = "--id", description = "a number", defaultValue = "1")
  private String id;

  @Option(names = "--url", description = "Vendure Shop API URL")
  private String url;

  @Override
  public Integer call() {
    try {
      String finalUrl = url;

      if (finalUrl == null || finalUrl.isEmpty()) {
        finalUrl = "http://localhost:3000/shop-api";
      }

      VendureService service = new VendureService(finalUrl);
      Product product = service.execute(new ProductIDQuery(id));
      System.out.println("Name: " + product.getName());
      System.out.println("Price: " + product.getPrice());

    } catch (Exception e) {
      e.printStackTrace();
      return 1;
    }

    return 0;
  }
}
