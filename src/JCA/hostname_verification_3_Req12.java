import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.net.URL;

public class hostname_verification_3_Req12 {
    public static void main(String[] args) {
        try {
            // Connect to the HTTPS server
            URL url = new URL("https://www.example.com");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            // Get server's SSL session
            SSLSession sslSession = conn.getSSLSocketFactory().createSocket(url.getHost(), 443).getSession();

            if (sslSession.isValid()) {
                System.out.println("SSL session is valid. Server's host name is: " + sslSession.getPeerHost());

                // Verify the server's hostname
                if (sslSession.getPeerHost().equalsIgnoreCase(url.getHost())) {
                    System.out.println("Server hostname verification passed.");
                } else {
                    System.out.println("Server hostname verification failed.");
                }
            } else {
                System.out.println("SSL session is not valid.");
            }
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}