package com.shikhov.music_analyzer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Server {
    private static HttpServer server;

    private Server() {}

    public static HttpServer getInstance() {
        if (server == null) {
            try {
                server = HttpServer.create(new InetSocketAddress(8080), 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return server;
    }

    public void start() {
        server.createContext("/redirect", exchange -> {
            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");

            String query = exchange.getRequestURI().getQuery();
            System.out.println(query);
            if (query == null) {
                System.out.println("Authentication error: The query is empty.");
                return;
            }
            List<NameValuePair> pairs = null;
            try {
                pairs = URLEncodedUtils.parse(new URI(query), StandardCharsets.UTF_8);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            for (NameValuePair pair : pairs) {
                System.out.printf("%s: %s", pair.getName(), pair.getValue());
            }
//                String response = "Q!";
//                exchange.sendResponseHeaders(200, response.getBytes(StandardCharsets.UTF_8).length);
//                OutputStream os = exchange.getResponseBody();
//                os.write(response.getBytes(StandardCharsets.UTF_8));
//                os.close();
        });
        server.setExecutor(null);
        server.start();
    }
}
