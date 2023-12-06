import javax.net.ssl.*;
import java.security.cert.X509Certificate;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

public class certificate_validation_3_Req22 {
    public static void main(String[] args) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");

            // Get a reference to the default trust manager
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);

            TrustManager[] defaultTrustManagers = trustManagerFactory.getTrustManagers();

            // Wrap default trust manager in one that does some validation
            TrustManager[] trustManagers = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            ((X509TrustManager) defaultTrustManagers[0]).checkClientTrusted(chain, authType);
                            // Place additional validation here
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                            ((X509TrustManager) defaultTrustManagers[0]).checkServerTrusted(chain, authType);
                            // Place additional validation here
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return ((X509TrustManager) defaultTrustManagers[0]).getAcceptedIssuers();
                        }
                    }
            };

            // Create new SSL context with our trust manager
            sslContext.init(null, trustManagers, new SecureRandom());

            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            // This can be used to create new SSL sockets
            // SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(socket, host, port, autoClose);
            
            // Validate certificates when you are making SSL/TLS connections with that socket.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}