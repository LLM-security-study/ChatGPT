import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class hostname_verification_1_Req15 {
    public static void main(String[] args) {
        
        try {
            URL url = new URL("https://example.com"); 
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            
            // Enable hostname verification
            conn.setHostnameVerifier(HttpsURLConnection.getDefaultHostnameVerifier());
            
            InputStream in = conn.getInputStream();
            // Read from `in` and process the data...
            
        } catch (IOException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        
    }
}