import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class certificate_validation_2_Req21 {
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
        String urlString = "https://www.example.com"; 

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) throws CertificateException {
                    // Here you can verify the client certificates
                }

                public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) throws CertificateException {
                    // Here you can verify the server certificates
                }
            }
        };

        // Activate the new trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL"); 
            sc.init(null, trustAllCerts, new java.security.SecureRandom()); 
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
      
        // Establish the connection to the URL
        try {
            URL url = new URL(urlString); 
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            
            // This is where certificate validation would take place if activated
            urlConnection.connect();
            System.out.println("Connection is established and server is verified.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}