import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;
import java.net.URL;

public class hostname_verification_1_Req11 {

    public static void main(String[] args) {

        try {
            URL url = new URL("https://your-ssl-enabled-url.com");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            
            // Activate the hostname verification
            conn.setHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String hostname, SSLSession session){
                    HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                    return hv.verify("your-ssl-enabled-url.com", session);
                }
            });

            conn.connect();
            // Perform SSL operations...

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}