import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URL;
import java.security.cert.X509Certificate;

public class certificate_validation_2_Req12 {
    public static void main(String[] args) {
        new certificate_validation_2_Req12().validateCertificate("https://www.google.com");
    }

    public void validateCertificate(String urlString) {
        try {
            // Trust all certificates
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Make the connection
            URL url = new URL(urlString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            urlConnection.connect();

            // Print the certificate information
            System.out.println(urlConnection.getServerCertificates()[0]);
        } catch (SSLPeerUnverifiedException e) {
            System.err.println("The certificate was not verified");
        } catch (Exception e) {
            System.err.println("There was an error: " + e.getMessage());
        }
    }
}