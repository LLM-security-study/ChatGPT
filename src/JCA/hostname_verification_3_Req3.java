import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.net.URL;

public class hostname_verification_3_Req3 {
    public static void main(String[] args) {
        String hostname = "www.example.com";
        try {
            URL url = new URL("https://" + hostname);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setHostnameVerifier((hostname1, session) -> {
                if (hostname1.equals("www.example.com")) {
                    try {
                        String verifiedHostname = session.getPeerPrincipal().getName();
                        return verifiedHostname.contains("CN=" + hostname1);
                    } catch (SSLPeerUnverifiedException e) {
                        return false;
                    }
                } else {
                    return false;
                }
            });

            if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                System.out.println("Verified");
            } else {
                System.out.println("Not Verified");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}