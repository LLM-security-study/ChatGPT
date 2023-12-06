import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class certificate_validation_2_Req28 {

    public static void main(String[] args) {

        try {
            // specify the url and create connection
            URL url = new URL("https://www.google.com");
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            
            // get the certificate from the server
            conn.connect();
            java.security.cert.Certificate[] certs = conn.getServerCertificates();
            
            System.out.println("Server certificate :");
            for(int i = 0; i < certs.length; i++){
                java.security.cert.Certificate cert=certs[i];
                System.out.println("Certificate is : " + cert.toString());
            }
            
            // disconnect the url connection after use
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}