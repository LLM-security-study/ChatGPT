import javax.net.ssl.*;
import java.net.URL;
import java.security.cert.X509Certificate;

public class certificate_validation_3_Req7 {
    public static void main(String[] args) throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        } };

        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

        URL url = new URL("https://your-url");
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();

        // dummy all trusting host verifier
        connection.setHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
      
        // Code to use the connection goes here.
    }
}