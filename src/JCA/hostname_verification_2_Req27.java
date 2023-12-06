import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_2_Req27 {
    public static void main(String[] args) {
        String hostname = "https://example.com";

        try {
            URL url = new URL(hostname);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            
            conn.setHostnameVerifier((hostname1, session) -> {
                if (hostname1.equals(session.getPeerHost())) {
                    return true;
                } else {
                    return false;
                }
            });
            
            conn.connect();
                
            System.out.println("Hostname Verification Passed for url: " + hostname);
        } catch (SSLPeerUnverifiedException e) {
            System.out.println("Hostname Verification Failed for url: " + hostname);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}