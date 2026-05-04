import java.util.List;

public class JsonFormatter implements Formatter {

    @Override
    public String format(List<Product> products) {
        String result = "[";

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);

            result += "{ \"name\": \"" + p.getName() + "\", \"price\": " + p.getPrice() + " }";

            if (i < products.size() - 1) {
                result += ", ";
            }
        }

        result += "]";
        return result;
    }
}