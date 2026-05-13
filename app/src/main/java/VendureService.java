import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class VendureService {
    private final String url;
    private final HttpClient client;

    public VendureService(String url) {
        this.url = url;
        this.client = HttpClient.newHttpClient();
    }

    public <T> T execute(GraphQLQuery<T> query) throws IOException, InterruptedException {
        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(query.getQuery()))
                        .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return query.parseResponse(response.body());
    }
}