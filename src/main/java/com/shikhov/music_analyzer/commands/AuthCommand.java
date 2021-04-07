package com.shikhov.music_analyzer.commands;

import com.shikhov.music_analyzer.Server;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class AuthCommand extends HttpCommand {
    @Override
    public void run() {
        System.out.println("https://accounts.spotify.com/authorize?client_id=a19ee7dbfda443b2a8150c9101bfd645&redirect_uri=http://localhost:8080&response_type=code");

        Server server = new Server();
        server.start();

        String authorizeURL = "https://accounts.spotify.com/authorize";
        String clientId = "eae6f970aa384cb59daf644e92f9434f";
        String responseType = "code";
        String redirectUri = "http://localhost:8080/redirect";

        authorizeURL += "?client_id=" + clientId + "&" + "response_type=code&redirect_uri=" + redirectUri;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(authorizeURL))
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
//            System.out.println(response.body());
        }
    }
}
