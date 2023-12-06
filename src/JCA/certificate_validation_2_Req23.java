import javax.net.ssl.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class certificate_validation_2_Req23 {
    private static final int HTTPS_PORT = 443;
    private static final String TRUSTSTORE_PASSWORD = "password";

    public static void main(String[] args) throws Exception {
        String hostname = "www.google.com";
        String truststorePath = "/path/to/your/truststore";

        SSLSocketFactory factory = getSSLSocketFactory(truststorePath, TRUSTSTORE_PASSWORD);

        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(new InetSocketAddress(hostname, HTTPS_PORT));
            SSLSocket socket = (SSLSocket) factory.createSocket(socketChannel.socket(),
                    socketChannel.socket().getInetAddress().getHostAddress(), socketChannel.socket().getPort(), true);

            // Initiates the TLS handshake
            socket.startHandshake();

            System.out.println("Certificate is valid");
        } catch (IOException e) {
            System.err.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static SSLSocketFactory getSSLSocketFactory(String truststorePath, String truststorePassword) throws Exception {
        // Load the trust store, the default Truststore type is "pkcs12", the alternative is "jks"
        KeyStore truststore = KeyStore.getInstance(KeyStore.getDefaultType());
        truststore.load(certificate_validation_2_Req23.class.getResourceAsStream(truststorePath), truststorePassword.toCharArray());

        // Get the singleton instance of the TrustManagerFactory
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

        // Itit the TrustManagerFactory using the truststore object
        trustManagerFactory.init(truststore);

        // Set up SSL context
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

        // Get socket factory
        SSLSocketFactory socketFactory = sslContext.getSocketFactory();
       
        return socketFactory;
    }
}