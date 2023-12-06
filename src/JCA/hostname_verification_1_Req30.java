import javax.net.ssl.HttpsURLConnection;
import java.net.URL;

public class hostname_verification_1_Req30 {
    public static void main(String[] args) {
        try {
            // Create a URL instance
            URL url = new URL("<insert-https-url-here>"); 
            
            // Open Https Connection
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                      
            // Enforce hostname verification
            connection.setHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier());

            // If we managed to open the connection without throwing exception, print success message
            System.out.println("Successfully connected to the secure server with host name verification enabled.");
            
            // Don't forget to close the connection at the end.
            connection.disconnect();
            
        } catch (Exception e) {
            // If there's any error, print out the error message
            System.out.println("Failure!! Unable to make secure connection!!\n" + e.getMessage());
        }
    }
}