import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;

public class hostname_verification_1_Req2 {

    public static void main(String[] args) {
        // Specify the URL you want to connect
        String urlString = "https://<yourURL>";

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                // check if the hostname equals the server's identification hostname
                if (hostname.equals(session.getPeerHost())) {
                    return true;
                }
                return false;
            }
        });

        try {
            URL url = new URL(urlString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

            // Open communication link (network traffic occurs here)
            urlConnection.connect();

            // Check for hostname verification
            if (urlConnection.getDefaultHostnameVerifier().verify(url.getHost(), urlConnection.getSSLSession())) {
                System.out.println("Hostname verification successful");
            } else {
                System.out.println("Hostname verification failed");
            }

            // disconnect after use
            urlConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SSLPeerUnverifiedException e) {
            System.out.println("Hostname verification failed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}