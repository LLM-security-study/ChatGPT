import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_3_Req13 {
    public static void main(String[] args) {
        String hostname = "https://www.example.com";
        try {
            URL url = new URL(hostname);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setHostnameVerifier((hostname1, session) -> true);
            conn.connect();

            if (conn.getHostnameVerifier().verify(hostname, conn.getSSLSession())) {
                System.out.println("Server Hostname is verified");
            } else {
                System.out.println("Server Hostname is not verified");
            }

        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}