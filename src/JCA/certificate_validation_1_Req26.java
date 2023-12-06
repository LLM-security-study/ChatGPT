import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.cert.X509Certificate;

public class certificate_validation_1_Req26 {

    public static void main(String[] args) {
        try {
            //set the trust store that has the server's public key
            System.setProperty("javax.net.ssl.trustStore", "myTrustStore.jts");

            URL url = new URL("https://securewebsite.com"); //Modify with your server URL
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            //Get certificate
            connection.connect();
            X509Certificate[] certs = (X509Certificate[]) connection.getServerCertificates();

            //Print certificate information
            for (X509Certificate cert : certs) {
                System.out.println("Certificate is: " + cert);
                System.out.println("The Public key is: " + cert.getPublicKey());
            }

            //Write certificate to the trust store for future connections.
            try (FileOutputStream fos = new FileOutputStream("myTrustStore.jts")) {
                for (X509Certificate cert : certs) {
                    fos.write(cert.getEncoded());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}