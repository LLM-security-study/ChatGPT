import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URL;
import java.security.cert.X509Certificate;

public class certificate_validation_1_Req25 {
  
   public static void main(String[] args) {
       new certificate_validation_1_Req25().validateCertificate("https://www.google.com");
   }

   public void validateCertificate(String urlStr) {
       try {
           // Create a trust manager that does not validate certificate chains
           TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
               public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
               public void checkClientTrusted(X509Certificate[] certs, String authType) { }
               public void checkServerTrusted(X509Certificate[] certs, String authType) { }
           }};
  
           // Install the all-trusting trust manager
           SSLContext sc = SSLContext.getInstance("SSL");
           sc.init(null, trustAllCerts, new java.security.SecureRandom());
           HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
  
           // Create all-trusting host name verifier
           HostnameVerifier allHostsValid = new HostnameVerifier() {
               public boolean verify(String hostname, SSLSession session) {
                   return true;
               }
           };
  
           // Install the all-trusting host verifier
           HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
      
           URL url = new URL(urlStr);
           HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
           System.out.println("Response Code: " + connection.getResponseCode());
           System.out.println("Cipher Suite Used: " + connection.getCipherSuite());
  
           connection.disconnect();
       } catch (Exception e) {
           if(e instanceof SSLHandshakeException)
               System.out.println("The Certificate is not valid.");
           else
               System.out.println("Error: " + e.getMessage());
       }
   }
}