import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class certificate_validation_3_Req3 {
    public static void main(String[] args) {
        try {
            // URL of the Host
            String host = "https://example.com";

            URL url = new URL(host);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // Get Server Certificate chain
            Certificate[] certs = conn.getServerCertificates();

            System.out.println("Number of certificates in the Chain : " + certs.length);
            for (Certificate cert: certs) {
                X509Certificate x = (X509Certificate) cert;
                System.out.println("Certificate : " + x.toString());

                // Validate the certificate
                x.checkValidity();

                System.out.println("Certificate is valid from " + x.getNotBefore() + " to " + x.getNotAfter());
                System.out.println("Certificate Public Key : " + x.getPublicKey());
                System.out.println("Certificate Issuer:  " + x.getIssuerDN());
                System.out.println("Certificate Version:  " + x.getVersion());
                System.out.println("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}