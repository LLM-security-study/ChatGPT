import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.net.URL;

public class hostname_verification_3_Req15 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://your-hostname-to-verify.com");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setHostnameVerifier((hostname, session) -> hostname.equals(session.getPeerHost()));

            urlConnection.connect();

            System.out.println("Connected to server with IP address: " + urlConnection.getInetAddress());

        } catch (SSLPeerUnverifiedException e) {
            System.out.println("SSL peer not verified. Connection is not safe.");
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}