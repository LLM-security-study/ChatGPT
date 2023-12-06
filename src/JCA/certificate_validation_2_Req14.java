import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class certificate_validation_2_Req14 {
    public static void main(String[] args) {
        try {
            //Specify the URL whose SSL/TLS certificate we want to validate.
            String urlString = "https://www.example.com";
            
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            
            //Initiate SSL handshake which implicitly validates the certificate because 
            //HttpsURLConnection by default validates the server's certificate against 
            //Java's truststore during the handshake.
            connection.connect();
            
            System.out.println("Certificate is valid. Connection established successfully.");
            connection.disconnect();
        } catch (Exception e) {
            System.out.println("Failed to establish SSL connection to server: " + e.getMessage());
        }
    }
}