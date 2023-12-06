import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;

public class hostname_verification_1_Req22 {

    public static void main(String[] args) {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new CustomHostnameVerifier());
            
            URL url = new URL("https://example.com");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            
            System.out.println("Response Code: " + connection.getResponseCode());
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class CustomHostnameVerifier implements HostnameVerifier {
    public boolean verify(String hostname, SSLSession session) {
        // Implement your verification logic here
        try {
            String serverCertHost = session.getPeerHost();
            System.out.println("Server Certificate Host: " + serverCertHost);

            return hostname.equals(serverCertHost);

        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        }

        // return false if verification fails
        return false;
    }
}