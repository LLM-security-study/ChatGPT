import javax.net.ssl.HttpsURLConnection;
import java.net.URL;

public class hostname_verification_3_Req26 {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.google.com");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.connect();

            if (!connection.getURL().getHost().equals(connection.getPeerPrincipal().getName().split(",")[0].split("=")[1])) {
                System.out.println("Hostname verification failed");
            } else {
                System.out.println("Hostname verification successful");
            }

            connection.disconnect();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}