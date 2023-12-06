import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.HostnameVerifier;
import java.net.URL;
import java.net.HttpURLConnection;

public class hostname_verification_2_Req10 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://your_url_here");

            // Create a context that doesn’t check certificates.
            SSLContext sslCtx = SSLContext.getInstance("TLS"); 
            sslCtx.init(null, null, null); 

            // From the context, create a HostnameVerifier that does nothing.
            HostnameVerifier hnVer = (hostname, session) -> true;

            // From the context, create a socket factory that does nothing.
            SSLSocketFactory sslSocketFactory = sslCtx.getSocketFactory();

            // Now put it all together to create a connection.
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(sslSocketFactory);
            connection.setHostnameVerifier(hnVer);

            HttpURLConnection.setFollowRedirects(false);
            connection.setConnectTimeout(7000); // 7 sec
            connection.setReadTimeout(7000); // 7 sec

            int responseCode = connection.getResponseCode();

            System.out.println("Response Code : " + responseCode);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}