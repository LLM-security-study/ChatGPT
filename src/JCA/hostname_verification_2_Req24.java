import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import java.net.URL;

public class hostname_verification_2_Req24 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://your-url-to-verify.com");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setHostnameVerifier((String hostname, SSLSession session) -> {
                if (!hostname.equals(session.getPeerHost())) {
                    System.err.println("Warning: Hostname of server certificate doesn't match with the hostname");
                }
                // Proceed even if the host name doesn't match
                return true;
            });

            conn.connect();

            // Do something with the connection

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}