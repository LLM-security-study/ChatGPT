import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;

public class certificate_validation_2_Req19 {

    //The Java entry point
    public static void main(String[] args) {

        String urlString = "https://www.google.com"; // your desired https URL
        try {
            URL url = new URL(urlString);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // Get the server's certificate chain
            Certificate[] certs = conn.getServerCertificates();

            System.out.println("Number of certificates: " + certs.length);
            for (Certificate cert : certs) {
                System.out.println("\tCertificate is: " + cert);
                //You can perform further validation of each certificate in this loop such as 
                //checking the issuer, validity, etc.

            }
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}