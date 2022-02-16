package main.Reto2;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import java.util.function.BiFunction;

public class EciSpark {
    public static void get(String path, BiFunction<HttpRequest, HttpResponse, String> biFunction) {
        EciSparkServer eciSparkServer = EciSparkServer.getInstance();
        eciSparkServer.get(path, biFunction);
    }

    public static void startServer() {
        EciSparkServer eciSparkServer = EciSparkServer.getInstance();
        eciSparkServer.startServer();
    }
}
