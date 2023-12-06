import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.security.cert.Certificate;

public class certificate_validation_3_Req21 {

    public static void main(String[] args) {
        new certificate_validation_3_Req21().testCertificateValidation("https://www.example.com/");  
    }

    public void testCertificateValidation(String httpsURL) {
        try {
            // Create a URL object
            URL url = new URL(httpsURL);

            // Create a connection 
            URLConnection urlConnection = url.openConnection();

            if(urlConnection instanceof HttpsURLConnection){
                HttpsURLConnection httpsConn = (HttpsURLConnection) urlConnection;

                // Make a GET request to the server
                httpsConn.setRequestMethod("GET");
                
                System.out.println("Response Code : " + httpsConn.getResponseCode());
                System.out.println("Cipher Suite : " + httpsConn.getCipherSuite());

                // Get the certificates
                Certificate[] certs = httpsConn.getServerCertificates();
                for(Certificate cert : certs){
                    System.out.println("Cert. Type : " + cert.getType());
                    System.out.println("Cert. Hash Code : " + cert.hashCode());
                    System.out.println("Cert. Public Key Algorithm : " 
                                    + cert.getPublicKey().getAlgorithm());
                    System.out.println("Cert. Public Key Format : " 
                                    + cert.getPublicKey().getFormat());
                }

            } else {
                System.out.println("Invalid URL");
            }
        }  catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}