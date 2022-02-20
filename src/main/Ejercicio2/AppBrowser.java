package main.Ejercicio2;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;



public class AppBrowser {
    String url;


    public static void main(String[] args) {
        AppBrowser browser = new AppBrowser();
    }

    /**
     * Genera una instancia de AppBrowser
     */
    AppBrowser() {
        this.url = this.askForUrl();
        String html = this.getHtmlFromUrl();
        writeData(html);

    }


    /**
     * Genera una instancia de AppBrowser
     * @param URL, URL a la cual se le van a encontrar los datos necesarios
     */
    public AppBrowser(String URL) {
        this.url = URL;
        String html = this.getHtmlFromUrl();
        writeData(html);
    }

    /**
     * Entrada de texto para encontrar la URL
     * @return URl leída
     */

    private String askForUrl() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una URL: ");
        String res = scanner.nextLine();
        scanner.close();

        return res;
    }

    /**
     * Datos de la URL
     * @return String con los datos de la URL
     */
    private String getHtmlFromUrl() {
        String htmlResponse = "";

        try {
            URL siteURL = new URL(this.url);

            URLConnection urlConnection = siteURL.openConnection();

            Map<String, List<String>> headers = urlConnection.getHeaderFields();

            Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();

            for(Map.Entry<String, List<String>> entry : entrySet){
                String headerName = entry.getKey();

                if (headerName != null) {
                    System.out.println(headerName + ": ");
                }

                List<String> headerValues = entry.getValue();

                for(String value: headerValues) {
                    System.out.println(value);
                }

            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(siteURL.openStream()));

            String inputLine = null;

            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
                htmlResponse += inputLine;
            }

        } catch (MalformedURLException e) {
            System.out.println("URL mal formada");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return htmlResponse;
    }

    /**
     * Escribe los datos en un archivo de texto
     * @param html, String con los datos a escribir
     */
    private void writeData(String html) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("resultado.html"));

            writer.write(html);

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}