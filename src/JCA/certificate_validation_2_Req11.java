import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

public class certificate_validation_2_Req11 {
    public static void main(String[] args) {
        try {
            // Load the JDK's cacerts keystore file
            FileInputStream is = new FileInputStream("<path-to-cacerts-file>");
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            String password = "<keystore-password>";
            keystore.load(is, password.toCharArray());

            // Create a TrustManager that trusts the certificates in our KeyStore
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keystore);

            // Create an SSLContext that uses our TrustManager
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

            // Use the SSLContext to create a SSLSocketFactory
            SSLSocketFactory sf = context.getSocketFactory();

            // Use the SSLSocketFactory to create an SSLSocket
            // Connect to the SSL server on port 443
            SSLSocket sslSocket = (SSLSocket) sf.createSocket("<server-address>", 443);

            // Start handshake
            sslSocket.startHandshake();

            // Now validate the certificate
            SSLSession session = sslSocket.getSession();
            java.security.cert.Certificate[] serverCerts = session.getPeerCertificates();

            // Iterate over each certificate
            for (int i = 0; i < serverCerts.length; i++) {
                java.security.cert.Certificate myCert = serverCerts[i];
                System.out.println("====Certificate:" + (i+1) + "====");
                System.out.println("-Public Key-\n" + myCert.getPublicKey());
                System.out.println("-Certificate Type-\n " + myCert.getType());
            }
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException |
                 IOException | CertificateException e) {
            // Log exceptions
            System.err.println("Exception: " + e.getMessage());
        }
    }
}