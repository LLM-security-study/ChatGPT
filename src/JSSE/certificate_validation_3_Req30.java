import javax.net.ssl.*;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class certificate_validation_3_Req30 {

    public static void main(String[] args) {
        try {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                        try {
                            certs[0].checkValidity();
                        } catch (Exception e) {
                            throw new CertificateException("Certificate not valid or trusted.");
                        }
                    }
                }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Now you can access an https URL without having the certificate in the truststore
            // try opening the URL 
            URL url = new URL("https://www.google.com");
            URLConnection urlConnection = url.openConnection();
            urlConnection.getContent();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}