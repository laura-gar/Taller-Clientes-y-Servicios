package main.Reto2;

import main.Reto1.Handler;
import main.Reto1.HttpServer;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.function.BiFunction;

public class EciSparkServer {
    private static EciSparkServer _instance;
    private HttpServer httpServer = new HttpServer();

    public static void main(String[] args) {
        EciSparkServer eciSparkServer = EciSparkServer.getInstance();
        eciSparkServer.startServer();
    }

    public static EciSparkServer getInstance() {
        if (_instance == null) {
            _instance = new EciSparkServer();
        }
        return _instance;
    }
    public void startServer() {
        httpServer.startServer();
    }
    public static String get(ArrayList<String> request, OutputStream outputStream) {
        try {
            String contentType = request.get(0).split(" ")[1];

            URI uri = new URI(contentType);

            if (uri.getPath().startsWith("/public")) {
                return Handler.prepareResponse(uri, outputStream);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return Handler.getDefaultResponse();
    }

    public static void get(String path, BiFunction<HttpRequest, HttpResponse, String> biFunction) {

    }

}
