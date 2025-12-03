package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.AppConfig;

import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;

public class ApiClient {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T get(String path, Class<T> clazz) throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(AppConfig.baseUrl() + path))
                .GET()
                .build();
        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(res.body(), clazz);
    }

    public static <T> T post(String path, Object body, Class<T> clazz) throws Exception {
        String json = mapper.writeValueAsString(body);
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(AppConfig.baseUrl() + path))
                .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(res.body(), clazz);
    }
}