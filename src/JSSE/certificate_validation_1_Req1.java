import javax.net.ssl.*;
import java.security.*;
import java.security.cert.CertificateException;

public class certificate_validation_1_Req1 {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
        TrustManagerFactory trustManagerFactory = null;

        try {
            // get the default TrustManager
            trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            // Here, we must initialize our TrustManagerFactory with a KeyStore of our choosing.
            trustManagerFactory.init((KeyStore) null);
        } catch (NoSuchAlgorithmException | KeyStoreException ex) {
            ex.printStackTrace();
        }

        // we create a new SSLContext and initialize it with a new TrustManager array, based on the TrustManagerFactory we just created
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());

        // We can now get a SSLSocketFactory, and apply the sslContext to it
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        try {
            // Now we can apply this SSL socket to a normal socket
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("localhost", 8080);
            // start handshake which includes certificate validation
            sslSocket.startHandshake();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}