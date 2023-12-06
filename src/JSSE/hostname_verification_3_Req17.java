import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_3_Req17 {
    
    public static void main(String[] args) {
        String urlString = "https://example.com"; // Replace the URL as needed
        try {
            URL url = new URL(urlString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setHostnameVerifier(hostname_verification_3_Req17::verifyHostName);
            
            urlConnection.connect();
            
            System.out.println("Successful connection to the server.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean verifyHostName(String hostname, SSLSession session) {
        try {
            String peerHost = session.getPeerHost();
            System.out.println("Peer Host: " + peerHost);
            
            if(!hostname.equals(peerHost)) {
                System.err.println("Host name verification failed!");
                return false;
            }
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Host name verification succeeded!");
        return true;
    }
}