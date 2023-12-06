import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;

public class hostname_verification_2_Req5 {

    public static void main(String[] args) {
        try {
            String hostname = "www.example.com";
            java.net.URL url = new java.net.URL("https://" + hostname);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // Set the custom hostname verifier
            conn.setHostnameVerifier(new CustomHostnameVerifier());

            conn.connect();
            System.out.println("Successfully connected to " + hostname);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class CustomHostnameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
        // We can specify the list of allowed hostnames here.
        // For this example, let's allow all hostnames.
        // To restrict hostnames, return false for non-allowed hostnames.

        System.out.println("Verifying hostname: " + hostname);
        return true;
    }
}