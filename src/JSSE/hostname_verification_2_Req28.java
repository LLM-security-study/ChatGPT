import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;

public class hostname_verification_2_Req28 {
    public static void main(String[] args) {
        new hostname_verification_2_Req28().begin();
    }

    private void begin() {
        try {
            // URL to be connected
            URL url = new URL("https://www.google.com");

            // Open HttpsURLConnection
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // Set HostnameVerifier
            conn.setHostnameVerifier(new MyHostnameVerifier());

            // Check if connection is successful
            if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                System.out.println("SSL Connection is established successfully!");
            } else {
                System.out.println("Failed to establish SSL Connection. Please check the URL or network status.");
            }
            // close connection
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            // Here, you can verify hostname, you can return true or false according to your business logic.
            // Return 'true' to consider the host name as verified and 'false' for not verified.

            // Indicating for the purpose of the example that we haven't carried out the necessary verification.
            System.out.println("Warning: hostname verification has to be coded according to your business requirements. This example accepts any host (potentially insecure).");

            return true; 
        }
    }
}