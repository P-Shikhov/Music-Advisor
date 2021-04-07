package com.shikhov.music_analyzer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Server {
    private static HttpServer server = null;

    static {
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        server.createContext("/redirect", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");

                String query = exchange.getRequestURI().getQuery();
                System.out.println(query);

//                String response = "Q!";
//                exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
//                OutputStream os = exchange.getResponseBody();
//                os.write(response.getBytes(StandardCharsets.UTF_8));
//                os.close();
            }
        });
        server.setExecutor(null);
        server.start();
    }
}
