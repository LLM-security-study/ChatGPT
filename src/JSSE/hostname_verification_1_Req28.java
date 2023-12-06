import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.net.URL;

public class hostname_verification_1_Req28 {

    public static void main(String[] args) {

        try {
            // Create a trustworthy trust manager
            SSLContext sslContext = SSLContext.getInstance("TLS");

            // Set up the HTTPs connection
            URL url = new URL("https://www.example.com");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // Activate the SSLContext
            connection.setSSLSocketFactory(sslContext.getSocketFactory());

            // This part will run the hostname verification
            connection.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    HostnameVerifier hv = HttpsURLConnection.getDefaultHostnameVerifier();
                    return hv.verify("www.example.com", session);
                }
            });

            System.out.println("Response Code : " + connection.getResponseCode());
            System.out.println("Cipher Suite : " + connection.getCipherSuite());

            connection.disconnect();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}