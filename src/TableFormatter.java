import java.util.List;

public class TableFormatter implements Formatter {

    @Override
    public String format(List<Product> products) {
        String result = "Name\t|\tPrice\n";
        result += "----------------------\n";

        for (Product p : products) {
            result += p.getName() + "\t|\t" + p.getPrice() + "\n";
        }

        return result;
    }
}
