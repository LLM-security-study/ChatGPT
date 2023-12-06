import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.HostnameVerifier;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_2_Req11 {
    public static void main(String[] args){
        try {
            // The target server URL
            URL url = new URL("https://your-server.com");
            
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            
            // Getting the default Hostname verification
            HostnameVerifier defaultVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
            
            // Setting the Hostname verification
            conn.setHostnameVerifier((hostname, session) -> {
                // Provide custom verification logic here.
                // This example just delegates back to the default verifier.
                return defaultVerifier.verify(hostname, session);
            });
            
            conn.connect();
            // If verification succeeded without throwing an exception, print a success message
            System.out.println("Hostname verification succeeded!");
            
        } catch (SSLPeerUnverifiedException e) {
            // This exception is thrown when the presented hostname did not pass verification
            e.printStackTrace();
        } catch (IOException e) {
            // Handle other IOExceptions appropriately for your application
            e.printStackTrace();
        }
    }
}