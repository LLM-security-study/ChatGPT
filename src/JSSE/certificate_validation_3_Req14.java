import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URL;
import java.security.cert.X509Certificate;

public class certificate_validation_3_Req14 {

    public static void main(String[] args) throws Exception {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());

        // Create all-trusting host name verifier
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // All set up, let's open a connection
        URL url = new URL("https://your_url");
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        
        // This example doesn't actually check any certificates, which is not secure.
        // Your verification code should be here.
        
        System.out.println("Successfully connected");

        urlConnection.disconnect();
    }
}