package advisor;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {
    public static String clientApi(String request) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .header("Authorization", "Bearer " + Controller.accessToken)
                .uri(URI.create(Controller.serverApiPath + request))
                .GET()
                .build();
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JsonElement check = JsonParser.parseString(response.body());
            if (check.getAsJsonObject().has("error")) {
                System.out.println(check.getAsJsonObject().get("error").getAsJsonObject().get("message").getAsString());
                return "error";
            }
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}