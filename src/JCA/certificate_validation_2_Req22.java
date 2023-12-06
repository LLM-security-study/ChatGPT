import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.net.MalformedURLException;
import java.io.IOException;

public class certificate_validation_2_Req22 {

  public static void main(String[] args) {
    new certificate_validation_2_Req22().inspectCertificate("https://www.google.com");  
  }

  public void inspectCertificate(String urlString){
      try {
          URL url = new URL(urlString);
          HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
          conn.connect();
          Certificate[] certificates = conn.getServerCertificates();
          
          for(Certificate certificate : certificates) {
              System.out.println("Certificate is: " + certificate);
              if(certificate instanceof X509Certificate) {
                  try {
                      ((X509Certificate)certificate).checkValidity();
                      System.out.println("Certificate is valid.");
                  } catch(Exception e) {
                      System.err.println("Certificate is not valid.");
                  }
              }
          }
      } catch (MalformedURLException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

}