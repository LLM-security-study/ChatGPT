import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URL;
import java.security.cert.X509Certificate;

public class certificate_validation_2_Req13 {

    public static void main(String[] args) throws Exception {

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Now you can access an https URL without having the certificate in the truststore
        try {
            URL url = new URL("https://www.yourdomain.com");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.connect();
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}