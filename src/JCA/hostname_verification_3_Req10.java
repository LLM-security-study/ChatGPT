import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class hostname_verification_3_Req10 {
    public static void main(String[] args) {
        // Specify the URL
        String urlString = "https://yourserver.com";

        try {
            // Create Connection Object
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // Get the hostname of the server
            String serverHostname = connection.getPeerPrincipal().getName();
            System.out.println("Server Hostname: " + serverHostname);

            // Verify that the hostname is as expected
            if (serverHostname.equals("yourserver.com")) {
                System.out.println("Hostname Verification Passed");
            } else {
                System.out.println("Hostname Verification Failed");
            }

            // Close the Connection
            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SSLPeerUnverifiedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}