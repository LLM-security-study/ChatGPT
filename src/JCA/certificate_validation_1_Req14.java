import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;

public class certificate_validation_1_Req14 {

    public static void main(String[] args) {
        try {
            // Build a KeyStore object which contains the server's certificate 
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("/path/to/keystore.jks"), "password".toCharArray());
            
            // Build a TrustManager which will ensure the authenticity of the certificate of the server
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);
            
            // Set the SSL context to use the TrustManager
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);
            
            // Create a Socket which connects to the server
            SSLSocketFactory sf = context.getSocketFactory();
            SSLSocket sslSocket = (SSLSocket) sf.createSocket("www.server.com", 1234);
            
            // Now you can use the sslSocket for a secure communication
            System.out.println("Connection is secure."); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}