import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class hostname_verification_1_Req4 {

    public static void main(String[] args) {
        new hostname_verification_1_Req4().ping();
    }

    private void ping() {
        String urlString = "https://example.com";
        InputStream stream = null;

        try {
            URL url = new URL(urlString);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setHostnameVerifier(new CustomHostnameVerifier());

            stream = urlConnection.getInputStream();
            System.out.println("Successfully connected to " + urlString + " over SSL/TLS.");
        } catch (IOException e) {
            System.out.println("Failed to verify hostname " + urlString + " over SSL/TLS.");
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class CustomHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            // Here you could check the hostname against a list of allowed ones.
            return true; // or false if the hostname does not match.
        }
    }
}