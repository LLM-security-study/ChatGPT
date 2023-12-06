import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import java.net.URL;

public class hostname_verification_2_Req6 {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // Create the SSL context for the connection
            SSLContext sslContext = SSLContext.getDefault();
            conn.setSSLSocketFactory(sslContext.getSocketFactory());

            // Initiates the SSL handshake
            conn.connect();

            System.out.println("Host name verification successful.");
        } catch (SSLHandshakeException e) {
            System.err.println("Host name verification failed: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}