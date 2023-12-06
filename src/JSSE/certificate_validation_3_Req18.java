import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class certificate_validation_3_Req18 {

    public static void main(String[] args) throws Exception {
        String trustStorePath = "path/to/your/truststore.jks";
        String trustStorePassword = "password";

        // Load the truststore, the default type is "jks"
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());

        // If the truststore password is empty, then invert the call to .load()
        try (FileInputStream fisTrust = new FileInputStream(trustStorePath)) {
            trustStore.load(fisTrust, trustStorePassword.toCharArray());
        }

        // Create a custom trust manager
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);

        // Setup the HTTPS context
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        // Perform connection and validation
        String httpsUrl = "https://example.com";
        HttpsURLConnection conn = (HttpsURLConnection) new URL(httpsUrl).openConnection();
        conn.setSSLSocketFactory(sslContext.getSocketFactory());

        // Connect to the server
        conn.connect();
        
        // After connecting to the server, you can check the servers certificate like this:
        try {
            java.security.cert.Certificate[] certs = conn.getServerCertificates();
            for (java.security.cert.Certificate cert : certs) {
                System.out.println("Cert Type : " + cert.getType());
                System.out.println("Cert Hash Code : " + cert.hashCode());
                System.out.println("Cert Public Key Algorithm : "
                        + cert.getPublicKey().getAlgorithm());
                System.out.println("Cert Public Key Format : "
                        + cert.getPublicKey().getFormat());
                System.out.println("\n");
            }
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
}