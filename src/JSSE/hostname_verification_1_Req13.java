import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;
import java.net.URL;

public class hostname_verification_1_Req13 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com");

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // set the HostnameVerifier
            conn.setHostnameVerifier(new CustomHostnameVerifier());

            System.out.println("Response Code: " + conn.getResponseCode());
            System.out.println("Cipher Suite: " + conn.getCipherSuite());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class CustomHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            // Implement your own verification logic here
            // Just as an example, we allow all hostnames
            return true;
        }
    }
}