import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_2_Req13 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setHostnameVerifier((hostname, session) -> hostname.equals("www.example.com"));

            conn.connect();

            if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                System.out.println("Connection established successfully.");
            } else {
                System.out.println("Failed to establish connection.");
            }

        } catch (SSLPeerUnverifiedException e) {
            System.err.println("Hostname verification failed: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
        }
    }
}