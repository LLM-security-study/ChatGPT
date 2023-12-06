import java.io.IOException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;

public class hostname_verification_1_Req9 {
 
    public static void main(String[] args) {
        new hostname_verification_1_Req9().run();
    }
  
    private void run() {
        try {
            URL url = new URL("https://www.example.com");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
  
            // Set your hostname verifier
            conn.setHostnameVerifier(new CustomHostnameVerifier());
  
            // Get response code to initiate SSL handshake
            System.out.println(conn.getResponseCode()); 
  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private class CustomHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			// Implement your hostname verification logic here
			// This example just allows anything, DO NOT use it in production
			return true;
		}
	}

}