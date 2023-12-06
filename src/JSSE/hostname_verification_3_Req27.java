import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_3_Req27 {
    
    public static void main(String[] args) {
        String hostname = "www.example.com";

        try {
            URL url = new URL("https://" + hostname);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setHostnameVerifier((hostname1, session) -> verifyHostname(hostname1, session));

            conn.connect();

            // Check the result
            if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                System.out.println(hostname + " has been verified successfully.");
            } else {
                System.out.println("Failed to verify " + hostname);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean verifyHostname(String hostname, SSLSession session) {
        if (hostname.equals(session.getPeerHost())) {
            try {
                String verifiedHostname = session.getPeerPrincipal().getName();
                verifiedHostname = verifiedHostname.substring(verifiedHostname.indexOf("CN=") + "CN=".length());
                return hostname.equals(verifiedHostname);
            } catch (SSLPeerUnverifiedException e) {
                return false;
            }
        }
        return false;
    }
}