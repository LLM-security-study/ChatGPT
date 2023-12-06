import java.net.URL;
import java.security.cert.Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class certificate_validation_3_Req25 {

    public static void main(String[] args) {
        new certificate_validation_3_Req25().testIt();
    }

    private void testIt() {

        String urlString = "https://www.google.com";

        try {

            URL url = new URL(urlString);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // get all headers
            System.out.println("\nResponse Header:");
            conn.getHeaderFields().forEach((k, v) -> System.out.println(k + ":" + v));

            // get the status code
            int statusCode = conn.getResponseCode();
            System.out.println("\nHTTP Status Code : " + statusCode);

            // get the certificate, if the connection is HTTPS
            if (conn instanceof HttpsURLConnection) {
                printServerCertificate(conn);
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printServerCertificate(HttpsURLConnection conn) {
        try {
            System.out.println("\nServer certificate:");
            for (Certificate cert : conn.getServerCertificates()) {
                System.out.println("Certificate is: " + cert.toString());
            }
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        }
    }
}