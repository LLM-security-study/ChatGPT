import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_1_Req1 {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.google.com");

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setHostnameVerifier(hostname_verification_1_Req1::verifyHostName);

            connection.getResponseCode(); // Make a request to enforce the hostname verification

            System.out.println("Hostname verification passed!");

        } catch (Exception e) {
            System.out.println("Exception occurred during hostname verification: " + e.getMessage());
        }
    }

    private static boolean verifyHostName(String hostname, SSLSession session) {
        try {
            String peerHost = session.getPeerHost();
            return hostname.equals(peerHost);
        } catch (SSLPeerUnverifiedException e) {
            return false;
        }
    }
}