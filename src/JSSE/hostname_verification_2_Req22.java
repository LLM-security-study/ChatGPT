import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_2_Req22 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setHostnameVerifier((hostname, session) -> {
                if (hostname.equals("www.example.com")) {
                    return true;
                }
                return false;
            });

            connection.connect();
            System.out.println("Connected.");
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}