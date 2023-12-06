import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_3_Req6 {

    public static void main(String[] args) {
        try {
            // Replace with your server URL
            URL url = new URL("https://www.example.com");
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();

            connection.setHostnameVerifier((hostname, session) -> hostname.equals(session.getPeerHost()));

            // Establishing the connection
            connection.connect();
            
            System.out.println("Hostname is verified");

        } catch (SSLPeerUnverifiedException e) {
            System.out.println("Host name not verified. "+ e.getMessage());
        } catch (IOException e) {
            System.out.println("Error occurred "+ e.getMessage());
        }
    }
}