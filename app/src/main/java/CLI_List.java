import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.List;
import java.util.concurrent.Callable;

@Command(name = "list", description = "Displays the list of products")
public class CLI_List implements Callable<Integer> {

    @Option(names = "--format", description = "table or json", defaultValue = "table")
    private String format;

    @Option(names = "--url", description = "Vendure Shop API URL")
    private String url;

    @Override
    public Integer call() {
        try {
            String finalUrl = url;

            if (finalUrl == null || finalUrl.isEmpty()) {
                finalUrl = "http://localhost:3000/shop-api";
            }

            Client client = new Client(finalUrl);
            List<Product> products = client.getProducts();

            Formatter formatter;

            if (format.equalsIgnoreCase("json")) {
                formatter = new JsonFormatter();
            } else {
                formatter = new TableFormatter();
            }

            System.out.println(formatter.format(products));
            return 0;

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return 1;
        }
    }
}