import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.net.URL;

public class hostname_verification_1_Req25 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://some-secure-url.com"); 
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setHostnameVerifier(new CustomHostnameVerifier()); 
            conn.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class CustomHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            // Implement your own hostname verification logic here.
            // The following is a very simple (and insecure) example. In a 
            // real-world application, you would definitely want more robust 
            // verification.
            return hostname.equals("some-secure-url.com"); 
        }
    }
}