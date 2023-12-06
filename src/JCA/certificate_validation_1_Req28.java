import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class certificate_validation_1_Req28 {
    public static void main(String[] args) {
        new certificate_validation_1_Req28().checkCertificate();
    }

    public void checkCertificate() {
        String httpsURL = "https://www.google.com"; // Specify your URL
        TrustManager[] trustAllCerts = new TrustManager[] {
           new X509TrustManager() {
              public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
              }
              public void checkClientTrusted(X509Certificate[] certs, String authType) {  }
              public void checkServerTrusted(X509Certificate[] certs, String authType) {  }
           }
        };

        try {
            URL url = new URL(httpsURL);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();

            // Create all-trusting SSL context
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            conn.connect();

            Certificate[] certs = conn.getServerCertificates();
            for(Certificate cert : certs){
                System.out.println("Certificate is: " + cert);
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}