import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

public class certificate_validation_2_Req8 {

    public static void main(String[] args) {
        String https_url = "https://www.google.com";
        URL url;
        try {

            url = new URL(https_url);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            System.out.println("Response Code : " + connection.getResponseCode());
            System.out.println("Cipher Suite : " + connection.getCipherSuite());
            System.out.println("\n");

            Certificate[] certs = connection.getServerCertificates();
            for (Certificate cert : certs) {
                System.out.println("Cert Type : " + cert.getType());
                System.out.println("Hash Code : " + cert.hashCode());
                System.out.println("Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
                System.out.println("Public Key Format : " + cert.getPublicKey().getFormat());
                System.out.println("\n");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        }

    }

}