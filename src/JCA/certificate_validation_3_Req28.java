import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class certificate_validation_3_Req28 {
    public static void main(String[] args) throws Exception {
        String host = "hostname";
        int port = 443;

        // Load the JDK's keystore
        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        trustStore.load(new FileInputStream("/path/to/your/truststore"), "password".toCharArray());

        // Create a trust manager that does not allow untrusted certificates
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);
        X509TrustManager defaultTrustManager = (X509TrustManager)tmf.getTrustManagers()[0];

        // Setup SSLContext with our trustManager
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(null, new TrustManager[]{defaultTrustManager}, null);

        // Create an SSLSocketFactory and SSLSocket
        SSLSocketFactory factory = ctx.getSocketFactory();
        SSLSocket sslSocket = (SSLSocket)factory.createSocket(host, port);

        // Start handshake, server's certificate will be automatically validated
        sslSocket.startHandshake();

        // Close the connection
        sslSocket.close();
    }

    private static class MyTrustManager implements X509TrustManager {
        private final X509TrustManager defaultTrustManager;

        MyTrustManager(X509TrustManager defaultTrustManager) {
            this.defaultTrustManager = defaultTrustManager;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            throw new CertificateException("Client certificates are not trusted");
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // Here you can implement your own logic to validate the server's certificate
            defaultTrustManager.checkServerTrusted(chain, authType);
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return defaultTrustManager.getAcceptedIssuers();
        }
    }
}