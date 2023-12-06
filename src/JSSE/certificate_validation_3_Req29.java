import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;

public class certificate_validation_3_Req29 {

    public static void main(String[] args) {
        
        try {
            // Create a trust manager factory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            // Initialize with the default key store
            tmf.init((KeyStore) null);

            // Create an SSL context
            SSLContext context = SSLContext.getInstance("TLS");
            
            // Initialize the context with the trust manager factory
            context.init(null, tmf.getTrustManagers(), null);

            // Create a connection
            URL url = new URL("https://www.google.com");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            
            // Set the socket factory
            conn.setSSLSocketFactory(context.getSocketFactory());

            // Connect
            conn.connect();

            // If it reaches here, certificate is valid.
            System.out.println("Certificate is valid.");

        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException e) {
            e.printStackTrace();
        }
    }
}