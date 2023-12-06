import java.net.*;
import javax.net.ssl.*;

public class hostname_verification_1_Req8 {
    public static void main(String[] args) {
        String targetUrl = "https://www.example.com";
        
        try {
            URL url = new URL(targetUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, new java.security.SecureRandom());
            
            conn.setSSLSocketFactory(sslContext.getSocketFactory());
            // An all-trusting host name verifier
            conn.setHostnameVerifier(new MyHostnameVerifier());
            
            conn.connect();
            System.out.println("Successfully connected to " + url.getHost());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Custom class to implement hostname verification.
    private static class MyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            // Custom verification logic
            // Here, we simply trust every server, which may not be secure in production.
            return true;
        }
    }
}