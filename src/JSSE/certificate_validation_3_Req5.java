import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class certificate_validation_3_Req5 {
    public static void main(String[] args) throws Exception {

        // load the truststore
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        try (FileInputStream fis = new FileInputStream("truststore.jks")) {
            trustStore.load(fis, "password".toCharArray());
        }

        // create a trust manager factory
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        try (SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("hostname", 443)) {
            sslSocket.startHandshake();

            // check the server's certificate chain
            SSLSession session = sslSocket.getSession();
            java.security.cert.Certificate[] servercerts = session.getPeerCertificates();
            for (int i = 0; i < servercerts.length; i++) {
                // You can check the validity of each certificate in the chain using X.509 specific features
                ((X509Certificate) servercerts[i]).checkValidity();
                System.out.println("Certificate " + (i+1) + ":");
                System.out.println("-------------------------------------------------");
                System.out.println(servercerts[i]);
            }
        }
    }
}