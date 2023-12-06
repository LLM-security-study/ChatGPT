import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

class certificate_validation_1_Req17 {

    public static void main(String[] args) {
        new certificate_validation_1_Req17().testIt();
    }

    private void testIt() {
        String httpsUrl = "https://www.google.com";
        URL url;
        try {
            url = new URL(httpsUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            printCertificateInfo(conn);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printCertificateInfo(HttpsURLConnection con) {
        if (con != null) {
            try {
                System.out.println("Response Code : " + con.getResponseCode());
                System.out.println("Cipher Suite : " + con.getCipherSuite());
                System.out.println("\n");

                Certificate[] certs = con.getServerCertificates();
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