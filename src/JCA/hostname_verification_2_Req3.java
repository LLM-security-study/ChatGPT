import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.net.URL;
import java.io.IOException;

public class hostname_verification_2_Req3 {
    public static void main(String[] args) {
        String httpsURL = "https://www.example.com";
        try {
            URL url = new URL(httpsURL);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setHostnameVerifier(new MyHostnameVerifier());
            conn.connect();
            System.out.println("Successful Connection!");
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}