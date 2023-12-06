import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

public class hostname_verification_1_Req27 {

    public static void main(String[] args) throws URISyntaxException {
        String urlString = "https://www.google.com";
        URI uri = new URI(urlString);
        try {
            HttpsURLConnection conn = (HttpsURLConnection) new URL(urlString).openConnection();
            conn.setHostnameVerifier((String hostname, SSLSession session) -> {
                if (!hostname.equals(uri.getHost())) {
                    throw new SSLException("Invalid Host");
                }
                return true;
            });
            conn.connect();

            System.out.println("Host verification success!");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
