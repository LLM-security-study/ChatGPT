import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;

public class hostname_verification_3_Req1 {
  public static void main(String[] args) {
    String hostname = "https://google.com";
    try {
      URL destinationURL = new URL(hostname);
      HttpsURLConnection conn = (HttpsURLConnection) destinationURL.openConnection();
      conn.connect();
      Certificate[] certs = conn.getServerCertificates();
      for (Certificate cert : certs) {
        if (cert instanceof X509Certificate) {
          try {
            Collection<List<?>> subjectAltNames = ((X509Certificate)cert).getSubjectAlternativeNames();
            if (subjectAltNames != null) {
              for (List<?> san : subjectAltNames) {
                if (san.get(1) instanceof String) {
                  String hostnameFromCertificate = (String) san.get(1);
                  System.out.println("Hostname from certificate: " + hostnameFromCertificate);
                }
              }
            }
          } catch (CertificateParsingException e) {
            e.printStackTrace();
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}