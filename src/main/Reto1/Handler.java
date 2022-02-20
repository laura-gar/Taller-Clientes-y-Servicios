package main.Reto1;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;

public class Handler {
    static String filesPath = "src/main/";

    /**
     * Respuesta por defecto
     * @return String con la respuesta por defecto en HTML
     */
    public static String getDefaultResponse() {
        String res = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Repuesta por Default</title>\n"
                + "</head>"
                + "<body>"
                + "<h2>Respuesta por Default</h2>"
                + "</body>"
                + "</html>";

        return res;
    }

    /**
     * Respuesta por no encontrado
     * @return String con la respuesta por no encontrado en HTML
     */
    public static String getNotFoundResponse(String message) {
        String res = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Respuesta no encontrada</title>\n"
                + "</head>"
                + "<body>"
                + "<h2>Respuesta no encontrada :( </h2>"
                + "<p>" + message + "</p>"
                + "</body>"
                + "</html>";

        return res;
    }

    /**
     * Verifica si es o no Binario extensión a revisar
     * @return Boolean
     */
    private static boolean isBinary(String extension) {
        return extension.equals("jpg") || extension.equals("png");
    }

    /**
     * Prepara la imagen para ser retornada
     * @param uri
     * @param outputStream
     * @return
     */
    private static String prepareImage(URI uri, OutputStream outputStream) {
        String response;
        String extension = uri.toString().substring(uri.getPath().lastIndexOf(".") + 1);

        String contentType = "application/json";

        response = "HTTP/1.1 200 OK \r\n"
                + "Content-Type: " + HttpServer.types.get(contentType.toUpperCase()) + "\r\n"
                + "\r\n";

        File file = new File(filesPath + uri.getPath());

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            ImageIO.write(bufferedImage, extension, byteArrayOutputStream);
            dataOutputStream.writeBytes(response);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * Prepara la respuesta para ser retornada
     * @param uri
     * @param outputStream
     * @return
     */
    public static String prepareResponse(URI uri, OutputStream outputStream) {
        String response;

        String extension = uri.toString();

        if (isBinary(extension)) {
            return prepareImage(uri, outputStream);
        }

        response = "HTTP/1.1 200 OK \r\n"
                + "Content-Type: " + HttpServer.types.get(extension.toUpperCase()) + "\r\n"
                + "\r\n";

        File file = new File(filesPath + uri.getPath());

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String fileLine;

            while ((fileLine = bufferedReader.readLine()) != null) {
                response += fileLine;
            }

        } catch (FileNotFoundException e) {
            return getNotFoundResponse(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
