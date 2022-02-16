import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLParser{
    public static void main(String[] args){
        try{
            URL googleURl = new URL("http://www.google.com:80/docs/index.html?size=8#ref");
            System.out.println("Host: " + googleURl.getHost());
            System.out.println("Port: " + googleURl.getPort());
            System.out.println("Authority: " +  googleURl.getAuthority());
            System.out.println("Path: " +  googleURl.getPath());
            System.out.println("Query: " +  googleURl.getQuery());
            System.out.println("File: " +  googleURl.getFile());
            System.out.println("Ref: " +  googleURl.getRef());



        }catch (MalformedURLException ex){
            Logger.getLogger(URLParser.class.getName()).log(Level.SEVERE, ex.getMessage());
        }
    }
}