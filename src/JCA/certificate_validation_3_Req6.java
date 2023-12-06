import javax.net.ssl.*;
import java.security.*;
import java.security.cert.*;

public class certificate_validation_3_Req6 {
    public static void main(String[] args) {
        
        try {
            // Set up SSL context
            SSLContext sslContext = SSLContext.getInstance("TLS");

            //TrustManagerFactory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init((KeyStore) null);  // Use default keystore
            TrustManager[] trustManagers = tmf.getTrustManagers();
            X509TrustManager defaultTm = (X509TrustManager) trustManagers[0];

            // Initialize SSLContext to work with TrustManagers
            sslContext.init(null, new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return defaultTm.getAcceptedIssuers();
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            defaultTm.checkServerTrusted(chain, authType);
                        }

                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            defaultTm.checkClientTrusted(chain, authType);
                        }
                    }
            }, null);

            // Use this SSLContext to initialize SSLSocket
            SSLSocketFactory factory = sslContext.getSocketFactory();
            SSLSocket socket = (SSLSocket) factory.createSocket("www.example.com", 443);
    
            //Here, the TLS/SSL handshake happens
            socket.startHandshake();
            
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException e) {
            e.printStackTrace();
        }
    }
}