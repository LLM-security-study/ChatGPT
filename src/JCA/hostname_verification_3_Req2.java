import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class hostname_verification_3_Req2 {
        
    public static void main(String[] args) {
        try {
            String urlString = "https://example.com"; // The URL to be verified
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            verifyHostname(connection, urlString);
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void verifyHostname(HttpsURLConnection connection, String url) {
        try {
            SSLSession session = connection.getSSLSession();
            if(session == null) {
                // No SSL session available
                System.out.println("SSL session not established. Cannot verify hostname for: " + url);
            } else {
                String hostname = session.getPeerHost(); // The name of the remote entity
                if(!url.contains(hostname)) {
                    System.out.println("Hostname verification failed. Expected: " + url + " but found: " + hostname);
                } else {
                    System.out.println("Hostname successfully verified: " + hostname);
                }
            }
        } catch (SSLPeerUnverifiedException e) {
            System.out.println("Hostname verification failed: Peer not verified." +  e.getMessage());
        }
    }
}