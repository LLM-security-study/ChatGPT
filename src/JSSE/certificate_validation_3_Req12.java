import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.cert.Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class certificate_validation_3_Req12 {

    public static void main(String[] args) {
        new certificate_validation_3_Req12().testIt();
    }

    private void testIt() {

        String urlString = "https://www.google.com";
        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.connect();
            Certificate[] certs = connection.getServerCertificates();
            for (Certificate cert : certs) {
                System.out.println("Certificate is: " + cert.toString());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String input;

            while ((input = br.readLine()) != null){
                System.out.println(input);
            }
            br.close();
            
        } catch (SSLPeerUnverifiedException e) {
            System.out.println("SSL peer unverified " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}