import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.net.URL;
import java.security.cert.Certificate;

public class hostname_verification_3_Req21 {
    public static void main(String[] args) {
        new hostname_verification_3_Req21().testSSLCert("https://www.google.com");
    }

    private void testSSLCert(String https_url) {
        try {
            URL url = new URL(https_url);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            System.out.println("URL : " + https_url);
            System.out.println("Response Code : " + conn.getResponseCode());
            System.out.println("Cipher Suite : " + conn.getCipherSuite());
            System.out.println("\nServer certificates :");

            Certificate[] certs = conn.getServerCertificates();
            for(Certificate cert : certs){
                System.out.println("Cert Type : " + cert.getType());
                System.out.println("Cert Hash Code : " + cert.hashCode());
                System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
                System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());
                System.out.println("\n");
            }

        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}