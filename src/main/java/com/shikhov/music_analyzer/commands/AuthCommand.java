package com.shikhov.music_analyzer.commands;

import com.shikhov.music_analyzer.HttpUtils;
import com.sun.net.httpserver.HttpServer;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

//import org.apache;

public class AuthCommand extends HttpCommand {

    private static final Charset CHARSET = StandardCharsets.UTF_8;
    final private String redirectUri = "http://localhost:8080/redirect";
    final private String clientId = "eae6f970aa384cb59daf644e92f9434f";
    final private String clientSecret = "ce87aabcee3f44e0afddda28d61a03aa";
    private String authorizationCode = null;

    static private HttpServer server = null;

    static {
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeTokens(){
        String postBody = String.format("grant_type=authorization_code&code=%s&redirect_uri=%s",
                authorizationCode, redirectUri);
        String encodedAuthString = Base64.getEncoder()
                .encodeToString(String.format("%s:%s", clientId, clientSecret).getBytes(StandardCharsets.UTF_8)) ;
        HttpRequest accessTokenRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString(postBody))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", String.format("Basic %s", encodedAuthString))
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(accessTokenRequest, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
//        todo: logging
        JSONObject jsonResponse = new JSONObject(Objects.requireNonNull(response.body()));
        HttpCommand.accessToken = jsonResponse.getString("access_token");
        HttpCommand.refreshToken = jsonResponse.getString("refresh_token");

    }

    @Override
    public void run() {
        server.createContext("/redirect", exchange -> {
            exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
            URI query = exchange.getRequestURI();
            List<NameValuePair> pairs = URLEncodedUtils.parse(query, StandardCharsets.UTF_8);
            authorizationCode = HttpUtils.getParamValue(pairs, "code");
            String responseString;
            if (authorizationCode == null) {
                String error = HttpUtils.getParamValue(pairs, "error");
                responseString = "Error granting access to the Music Advisor app.";
                exchange.sendResponseHeaders(401, responseString.getBytes(CHARSET).length);
                if (error == null) {
                    responseString = String.format("Malformed authorization response URI: %s", query);
                    exchange.sendResponseHeaders(400, responseString.getBytes(CHARSET).length);
                }
            } else {
                responseString = "You have granted access to the Music Advisor app.";
                exchange.sendResponseHeaders(200, responseString.getBytes(CHARSET).length);
                initializeTokens();
            }
            exchange.getResponseBody().write(responseString.getBytes());
            exchange.getResponseBody().close();
        });

        server.setExecutor(null);
        server.start();

        String authorizeURL = "https://accounts.spotify.com/authorize";
        //todo: add state
        authorizeURL += "?client_id=" + clientId + "&" + "response_type=code&redirect_uri=" + redirectUri;

        System.out.println("use this link to request the access code:");
        System.out.println(authorizeURL);



    }
}
