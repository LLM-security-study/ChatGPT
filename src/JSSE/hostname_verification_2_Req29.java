import java.net.URL;
import java.net.HttpsURLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class hostname_verification_2_Req29 {

    public static void main(String[] args) {
        
        try {
            URL url = new URL("https://www.example.com");
            HttpsURLConnection urlConn = URLConnection.openConnection();

            urlConn.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    if (hostname.equals("www.example.com")) {
                        return true;
                    }
                    return false;
                }
            });

            System.out.println("Response Code: " + urlConn.getResponseCode());
            System.out.println("SSL Session : " + urlConn.getSSLSession());
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}