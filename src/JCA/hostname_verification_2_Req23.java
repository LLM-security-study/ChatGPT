import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

public class hostname_verification_2_Req23 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.example.com");
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();

            conn.setHostnameVerifier((hostname, session) -> {
                if (hostname.equals("www.example.com")) {
                    return true;
                }
                return false;
            });

            conn.connect();

            System.out.println("Connection successful!");

        } catch (SSLPeerUnverifiedException e) {
            System.out.println("Host verification failed: "+e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}