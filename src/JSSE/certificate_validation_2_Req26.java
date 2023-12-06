import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.Certificate;

public class certificate_validation_2_Req26 {

    public static void main(String[] args) {

        try {
            URL url = new URL("https://www.example.com"); 

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            
            Certificate[] certificates = connection.getServerCertificates();

            System.out.println("Server's certificate information: ");
            for (Certificate cert : certificates) {
                System.out.println("\tCertificate Type : " + cert.getType());
                System.out.println("\tHash Code : " + cert.hashCode());
                System.out.println("\tPublic Key : " + cert.getPublicKey());
                System.out.println("\tCertificate Encoding: " + cert.getEncoded());
            }
            
            connection.disconnect();

        } catch (SSLHandshakeException e) {
            System.out.println("Could not establish SSL connection. Certificate validation failed.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}