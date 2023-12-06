import java.io.*;
import java.security.*;
import java.security.cert.*;
import javax.net.ssl.*;

public class key_storage_3_Req19 {

    public static void main(String[] args) {
        try {
            char[] password = "storepassword".toCharArray();  // Use your own keystore password
            String host = "your_host";  // Specify your host
            int port = 443;  // Default HTTPS port

            // Create a new SSL context 
            SSLContext context = SSLContext.getInstance("TLS");
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null, password);
            kmf.init(ks, password);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init((KeyStore) null);
            context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
            SSLSocketFactory factory = context.getSocketFactory();

            // Connect to the host and get the certificate
            SSLSocket socket = (SSLSocket) factory.createSocket(host, port);
            socket.startHandshake();
            SSLSession session = socket.getSession();
            Certificate[] peerCerts = session.getPeerCertificates();

            // Load the keystore and add the certificate
            ks.setCertificateEntry("alias-of-cert", peerCerts[0]);  // Choose your own alias for the certificate
            FileOutputStream fos = new FileOutputStream("keystore.jks");  // Specify your keystore file name
            ks.store(fos, password);
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}