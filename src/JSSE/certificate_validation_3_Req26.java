import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class certificate_validation_3_Req26 {
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        String keyStoreFile = "/path/to/keystore.jks";
        char[] keyPassword = "password".toCharArray();

        // Load the JKS file (using the standard SunX509 format)
        KeyStore keyStore = KeyStore.getInstance("JKS");
        FileInputStream keyInput = new FileInputStream(keyStoreFile);
        keyStore.load(keyInput, keyPassword);
        keyInput.close();

        // Initialize a TrustManagerFactory with the trusted store
        TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustFactory.init(keyStore);

        // Get the TrustManagers from the factory
        TrustManager[] trustManagers = trustFactory.getTrustManagers();

        // Initialize an SSLContext with the trust managers
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagers, null);

        // Get the SSLSocketFactory from the SSLContext
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        // Connect to the server's IP and PORT
        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("server_ip", 443); // Replace server_ip with your server's IP.

        // Hand shaking section
        try {
            sslSocket.startHandshake();
            System.out.println("TLS Handshake success");
        } catch (IOException e) {
            System.out.println("Handshake failed");
            e.printStackTrace();
        }
    }
}