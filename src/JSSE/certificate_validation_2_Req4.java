import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;

public class certificate_validation_2_Req4 {
    public static void main(String[] args) {

        String https_url = "https://www.google.com/";
        URL url;
        try {

            url = new URL(https_url);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();

            conn.setSSLSocketFactory((SSLSocketFactory)SSLSocketFactory.getDefault());

            print_https_cert(conn);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void print_https_cert(HttpsURLConnection con){
        if(con!=null){
            try {
                System.out.println("Response Code : " + con.getResponseCode());
                System.out.println("Cipher Suite : " + con.getCipherSuite());

                Certificate[] certs = con.getServerCertificates();
                for(Certificate cert : certs){
                    System.out.println("Cert Type : " + cert.getType());
                    System.out.println("Cert Hash Code : " + cert.hashCode());
                    System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
                    System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}