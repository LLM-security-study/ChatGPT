import javax.net.ssl.*;
import java.security.cert.Certificate;

public class certificate_validation_1_Req22 {
    public static void main(String[] args) {

        try {
            // create a URL object
            String https_url = "https://example.com/";
            URL url = new URL(https_url);

            // create a SSL connection object
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();

            // print the SSL details
            printSSLDetails(conn);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void printSSLDetails(HttpsURLConnection con) {
        if(con!=null){
            try {
                System.out.println("Response Code : " + con.getResponseCode());
                System.out.println("Cipher Suite : " + con.getCipherSuite());
                System.out.println("\n");

                Certificate[] certs = con.getServerCertificates();
                for(Certificate cert : certs){
                    System.out.println("Cert Type : " + cert.getType());
                    System.out.println("Cert Hash Code : " + cert.hashCode());
                    System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
                    System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());
                    System.out.println("\n");
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}