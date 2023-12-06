import javax.net.ssl.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class certificate_validation_1_Req5 {
    public static void main(String[] args) {
        try {
            TrustManager[] trustAllCertificates = new TrustManager[] {
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                        // we would normally validate the client cert here.
                    }

                    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                        // we would normally validate the server cert here.
                    }
                }
            };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCertificates, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            
            //HttpsURLConnection connection = (HttpsURLConnection) new URL("https://your-url-here").openConnection();
            // Perform networking operations with 'connection' here.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}