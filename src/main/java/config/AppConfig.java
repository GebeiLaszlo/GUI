package config;

public class AppConfig {
    private static String host = "localhost";
    private static int port = 8080;

    public static String baseUrl() {
        return "http://" + host + ":" + port;
    }

    public static void setHost(String h) { host = h; }
    public static void setPort(int p) { port = p; }
}
