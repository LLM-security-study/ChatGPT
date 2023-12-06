import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.security.cert.Certificate;
import java.io.*;

public class certificate_validation_2_Req10 {
    public static void main(String[] args) {
        new certificate_validation_2_Req10().testIt();
    }

    private void testIt() {
        String https_url = "https://www.google.com/";
        URL url;
        try {
            url = new URL(https_url);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            if (conn != null) {
                print_certificate_info(conn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void print_certificate_info(HttpsURLConnection conn) {
        if (conn != null) {
            try {
                System.out.println("Response Code : " + conn.getResponseCode());
                System.out.println("Cipher Suite : " + conn.getCipherSuite());
                System.out.println("\n");

                Certificate[] certs = conn.getServerCertificates();
                for (Certificate cert : certs) {
                    System.out.println("Cert Type : " + cert.getType());
                    System.out.println("Cert Hash Code : " + cert.hashCode());
                    System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
                    System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());
                    System.out.println("\n");
                }
            } catch (SSLPeerUnverifiedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}