package main.Reto2;

import com.sun.net.httpserver.HttpServer;

public class git EciSparkServer {
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
}
