package com.shikhov.music_analyzer.commands;

import java.net.http.HttpClient;

public abstract class HttpCommand implements Command {
    static HttpClient httpClient;

    static {
        httpClient = HttpClient.newHttpClient();
    }
}
