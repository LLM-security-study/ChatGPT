import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

public class certificate_validation_1_Req7 {

    public static void main(String[] args) {
        new certificate_validation_1_Req7().testIt();
    }

    private void testIt() {
        String urlString = "https://yourwebsite.com";
        URL url;
        BufferedReader reader;

        try {
            url = new URL(urlString);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // Get server certificates
            Certificate[] certs = conn.getServerCertificates();
            for (Certificate cert : certs) {
                System.out.println("Certificate is: " + certs[0]);
                // you can validate the certificate here  
            }

            // read and print the response
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}