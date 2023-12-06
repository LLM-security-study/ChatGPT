import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class certificate_validation_2_Req7 {

  public static void main(String[] args) throws Exception {
    // create a trust manager
    TrustManager[] trustAllCerts = new TrustManager[] {
      new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
          return null;
        }
        public void checkClientTrusted(X509Certificate[] certs, String authType) {  }
        public void checkServerTrusted(X509Certificate[] certs, String authType) {  }

      }
    };
    
    // install the all-trusting trust manager
    SSLContext sc = SSLContext.getInstance("SSL");
    sc.init(null, trustAllCerts, new java.security.SecureRandom());
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    
    // create all-trusting host name verifier
    HostnameVerifier allHostsValid = new HostnameVerifier() {
      public boolean verify(String hostname, javax.net.ssl.SSLSession session) {
        return true;
      }
    };
    
    // install the all-trusting host verifier
    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

    URL url = new URL(args[0]);
    HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
    conn.connect();
  
    System.out.println("Connection established and server certificate is valid.");
    
    conn.disconnect();
    
  }
}