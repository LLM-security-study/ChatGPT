import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.net.URL;

public class hostname_verification_3_Req20 {
    public static void main(String[] args) {
        String urlStr = "https://your-server.com";  // Replace with your server's URL

        try {
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setHostnameVerifier((hostname, session) -> hostname.equals(session.getPeerHost()));

            conn.connect();

            System.out.println("Successfully verified the hostname");

        } catch (SSLPeerUnverifiedException e) {
            System.err.println("Hostname cannot be verified.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}