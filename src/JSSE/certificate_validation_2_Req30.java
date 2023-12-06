import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;

public class certificate_validation_2_Req30 {

  public static void main(String[] args) {

    String urlStr = "https://www.example.com";
    try {
      URL url = new URL(urlStr);
      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
      conn.connect();

      // Get server certificates
      Certificate[] certs = conn.getServerCertificates();
      System.out.println("Server certificates :");
      for (Certificate cert : certs) {
        System.out.println("Cert Type : " + cert.getType());
        System.out.println("Cert Hash Code : " + cert.hashCode());
        System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
        System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());
        System.out.println("\n");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}