import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_3_Req7 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://server.com"); //Replace with the server's URL
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setHostnameVerifier((hostname, session) -> hostname.equals(session.getPeerHost()));
            conn.connect();
            System.out.println("Server's hostname verified successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failure in verifying server's hostname.");
        }
    }
}