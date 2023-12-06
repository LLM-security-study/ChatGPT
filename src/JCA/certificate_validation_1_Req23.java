import java.net.URL;
import java.net.HttpsURLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.security.cert.X509Certificate;

public class certificate_validation_1_Req23 {
    public static void main(String[] args) throws Exception {
        String urlString = "<Your_URL>";

        // Create a trust manager that does not validate certificate chains
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

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Open connection and read
        URL url = new URL(urlString);
        HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
        urlConnection.connect();
        int code = urlConnection.getResponseCode(); 
        System.out.println("Response Code: " + code);

        urlConnection.disconnect();
    }
}