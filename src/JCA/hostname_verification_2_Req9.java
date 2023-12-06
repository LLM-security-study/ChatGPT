import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_2_Req9 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setHostnameVerifier((hostname, session) -> hostname.equals("www.example.com")); // Set your expected hostname here

            conn.connect();

            // If the hostname doesn't match the certificate hostname, it will throw an SSLPeerUnverifiedException
            System.out.println("Connection established. The host is verified.");
        } catch (SSLPeerUnverifiedException e) {
            System.out.println("SSLPeerUnverifiedException was thrown. The host is not verified.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException thrown.");
            e.printStackTrace();
        }
    }
}