package main.Reto2;

import java.net.http.*;


import java.util.function.BiFunction;

public class EciSpark {
    public  void get(String path, BiFunction<HttpRequest, HttpResponse, String> biFunction) {
        EciSparkServer eciSparkServer = EciSparkServer.getInstance();
        eciSparkServer.get(path, biFunction);
    }

    public static void startServer() {
        EciSparkServer eciSparkServer = EciSparkServer.getInstance();
        eciSparkServer.startServer();
    }
}
