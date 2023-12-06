import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.net.URL;
import java.io.IOException;

public class hostname_verification_2_Req25 {
    
    public static void main(String[] args) {
        String urlString = "https://www.yourwebsite.com";

        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // enable hostname verification
            connection.setHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier());

            // Check if hostname verification is successful
            connection.connect();
            int responseCode = connection.getResponseCode();
            if(responseCode == HttpsURLConnection.HTTP_OK){
                System.out.println("Hostname verification passed.");
            } else {
                System.out.println("Hostname verification failed. Response Code: " + responseCode);
            }

            connection.disconnect();
        } catch(IOException ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}