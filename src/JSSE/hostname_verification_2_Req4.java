import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;

public class hostname_verification_2_Req4 {

    public static void main(String[] args) {
        try {
            // create a url that refers to a page on ssl protected site
            URL url = new URL("https://www.yoursite.com");

            // open connection
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // set the HostnameVerifier
            conn.setHostnameVerifier(new CustomHostnameVerifier());

            // connection successful if we reach this point
            System.out.println("Connection Success");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//HostnameVerifier implementation
class CustomHostnameVerifier implements HostnameVerifier {
    public boolean verify(String hostname, SSLSession session) {
        // verify if the hostname is as expected or not
        if (hostname.equals("www.yoursite.com")) {
            return true;
        }
        return false;
    }
}