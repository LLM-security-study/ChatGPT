import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.X509Certificate;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;

public class certificate_validation_3_Req16 {
    public static void main(String[] args) {
        try {
            // Load the JDK's cacerts keystore file
            String filename = System.getProperty("java.home") + "/lib/security/cacerts".replace('/', File.separatorChar);
            FileInputStream is = new FileInputStream(filename);
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            String password = "changeit"; // password for cacerts keystore
            keystore.load(is, password.toCharArray());

            // Create a TrustManager that trusts the CAs in our KeyStore
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keystore);
            X509TrustManager defaultTrustManager = (X509TrustManager)tmf.getTrustManagers()[0];

            // Establish a SSL/TLS connection with the server
            URL url = new URL("https://your-server.com");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[]{ defaultTrustManager }, null);
            urlConnection.setSSLSocketFactory(context.getSocketFactory());

            // confirm the server's certificate chain is validated
            InputStream in = urlConnection.getInputStream();
            System.out.println("Server's certificate chain is validated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}