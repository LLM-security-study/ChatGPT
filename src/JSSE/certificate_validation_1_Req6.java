import java.net.*;
import javax.net.ssl.*;
import java.io.*;

public class certificate_validation_1_Req6 {
    public static void main(String[] args) {
        try {
            String urlString = "https://example.com";
            URL url = new URL(urlString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            // Create an SSLSocketFactory
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, null, null);
            SSLSocketFactory sslSocketFactory = context.getSocketFactory();

            // Set our connection to use this socket factory
            urlConnection.setSSLSocketFactory(sslSocketFactory);
            
            // Verifying
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                System.out.println("Successfully connected to the server!");
            } else {
                System.out.println("Failed to connect to the server!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}