import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URL;
import java.security.cert.X509Certificate;

public class certificate_validation_2_Req27 {
    public static void main(String[] args) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManager[] trustManagerArray = {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        // Here is where the validation should be done.
                        // Write whatever check you want to perform on the provided certificate. 
                        // Throw a CertificateException if the certificate is not trusted.
                    }
                }
            };

            sslContext.init(null, trustManagerArray, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

            URL url = new URL("https://your-secure-api-url.com");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            
            System.out.println("Response Code : " + connection.getResponseCode());
            System.out.println("Cipher Suite : " + connection.getCipherSuite());
            System.out.println("\n\nCertificate Information : ");
            connection.getServerCertificates().forEach(System.out::println);
            connection.disconnect();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}