import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.net.URL;

public class hostname_verification_1_Req17 {
    public static void main(String[] args) throws Exception {
        // URL of the host
        String urlString = "https://www.example.com";
        
        URL url = new URL(urlString);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

        // Set Hostname verification
        urlConnection.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                if (hostname.equals("www.example.com")) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        
        try {
            // Do connection operations (Send/Receive data)
            // ...
        } finally {
            // Close connection
            urlConnection.disconnect();
        }
    }
}