import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.net.URL;

public class hostname_verification_1_Req14 {
    public static void main(String[] args) {
        try {
            //your desired URL
            String urlString = "https://example.com";
            
            URL url = new URL(urlString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            //using lambda expression to define the new HostnameVerifier
            urlConnection.setHostnameVerifier((hostname, session) -> {
                // implement your verification rules here
                // this is a sample rule that verifies if hostname is "example.com"
                return "example.com".equals(hostname);
            });

            // check authentication
            int responseCode = urlConnection.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            
            urlConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}