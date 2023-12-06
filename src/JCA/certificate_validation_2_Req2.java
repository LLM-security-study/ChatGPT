import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class certificate_validation_2_Req2 {
  public static void main(String[] args) {
    try {
      URL url = new URL("https://www.example.com"); // replace with your https url
      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
      conn.connect();

      Certificate[] certs = conn.getServerCertificates();

      System.out.println("Number of certificates: " + certs.length);
      for (Certificate cert : certs) {
        if (cert instanceof X509Certificate) {
          X509Certificate x = (X509Certificate) cert;
          System.out.println("Certificate issuer: " + x.getIssuerDN());
          System.out.println("Certificate subject: " + x.getSubjectDN());
          System.out.println("Certificate serial number: " + x.getSerialNumber());
        } else {
          System.out.println("Unknown certificate type: " + cert);
        }
      }

      conn.disconnect();

    } catch (IOException ex) {
      System.out.println("IOException : " + ex.getMessage());
    }
  }
}