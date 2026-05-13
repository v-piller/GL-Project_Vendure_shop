import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TableFormatterTest {

    @Test
    void testTableFormatter() {
        List<Product> products = List.of(
                new Product("Laptop", 1000),
                new Product("Tablet", 500)
        );

        Formatter formatter = new TableFormatter();
        String result = formatter.format(products);

        assertTrue(result.contains("Laptop"));
        assertTrue(result.contains("1000"));
    }
}