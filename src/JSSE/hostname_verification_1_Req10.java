import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;

public class hostname_verification_1_Req10 {
    public static void main(String[] args) {
        new hostname_verification_1_Req10().hostnameVerification();
    }

    public void hostnameVerification() {
        try {
            String urlStr = "https://yourdomain.com";
            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    if (hostname.equals("yourdomain.com"))  // Replace "yourdomain.com" with the actual domain you wish to allow.
                        return true;
                    return false;
                }
            });

            conn.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}