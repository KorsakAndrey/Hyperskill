package advisor.auth;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Front implements HttpHandler {
    private static String code;
    private static CountDownLatch latch;

    Front(CountDownLatch latch) {
        Front.latch = latch;
    }

    public static String getCode() {
        return code;

    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        code = exchange.getRequestURI().toString();
        if (code.contains("=")){
            code = code.split("=")[1];
        } else {
            code = "error";
        }
        String message;
        if(code.equals("access_denied") || code.equals("error")){
            System.out.println("access denied");
            message = "Authorization code not found. Try again.";
        } else {
            System.out.println("code received");
            message = "Got the code. Return back to your program.";
            latch.countDown();
        }
        exchange.sendResponseHeaders(200, message.length());
        exchange.getResponseBody().write(message.getBytes());
        exchange.getResponseBody().close();
    }
}
