import java.net.HttpsURLConnection;
import java.net.URL;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class hostname_verification_2_Req8 {
    public static void main(String[] args) {
        try {
            // Create a URL object
            URL url = new URL("https://www.example.com");
            
            // Open connection
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // Initiate hostname verification
            connection.setHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    // Hostname verification logic
                    if (hostname.equals("www.example.com")) {
                        return true;
                    }
                    return false;
                }
            });
            
            connection.connect();

            System.out.println("Successfully connected to the server.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}