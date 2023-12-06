import javax.net.ssl.HttpsURLConnection;
import java.net.URL;

public class hostname_verification_3_Req8 {
    public static void main(String[] args) throws Exception {

        String hostname = "www.google.com";
        int port = 443;
        String urlStr = "https://" + hostname + ":" + port;

        URL url = new URL(urlStr);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

        conn.setHostnameVerifier((hostname1, session) -> {
            if (hostname1.equals("www.google.com")) {
                return true;
            }
            return false;
        });

        // Do something with the connection
        conn.connect();

        System.out.println(conn.getResponseCode());
        conn.disconnect();

    }
}