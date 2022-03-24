package advisor.auth;

import advisor.Controller;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.concurrent.CountDownLatch;

public class Auth implements Runnable{
    private final CountDownLatch latch = new CountDownLatch(1);
    private final String id = "489f92295f2a4288a83d466dd2f6a6dd";
    private final String secret = "5831c0ef74254c0da5be94c8e51c28fb";
    private final String encodedString = Base64.getEncoder().encodeToString((id + ":" + secret).getBytes());
    private boolean isAuth = false;
    private JsonElement jsonElement;
    private final String url;


    public JsonElement getJsonElement() {
        return jsonElement;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public Auth(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        Front front = new Front(latch);

        try {
            System.out.println("use this link to request the access code:\n" +
                    url + "/authorize?" +
                    "client_id=" + id + "&" +
                    "redirect_uri=http://localhost:8080&response_type=code");
            HttpServer server = HttpServer.create();
            server.bind(new InetSocketAddress(8080), 0);
            server.createContext("/", front);
            server.start();
            System.out.println("waiting for code...");
            latch.await();
            server.stop(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("making http request for access_token...");
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization", "Basic " + encodedString) //<base64 encoded client_id:client_secret>
                    .uri(URI.create(url + "/api/token"))
                    .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code&" +
                            "redirect_uri=http://localhost:8080&" +
                            "code=" + Front.getCode()))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonElement = JsonParser.parseString(response.body());
            if(jsonElement.getAsJsonObject().has("access_token")) {
                Controller.accessToken = jsonElement.getAsJsonObject().get("access_token").getAsString();
                isAuth = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
