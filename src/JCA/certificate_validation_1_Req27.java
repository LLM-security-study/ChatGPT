import java.net.URL;
import java.net.HttpsURLConnection;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class certificate_validation_1_Req27 {
    public static void main(String[] args) {
        try {
            // Load the KeyStore file
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new FileInputStream("yourKeystore.jks"), "yourPassword".toCharArray());

            // Get factory and trust managers
            TrustManagerFactory tmFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmFactory.init(keyStore);
            TrustManager[] tm = tmFactory.getTrustManagers();

            // Create new trust manager that trust all certificates
            X509TrustManager defaultTM = 
                (tm == null || tm.length == 0) ? 
                null : 
                (X509TrustManager) tm[0];

            // Set up the HTTPS context and parameters
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{defaultTM}, null);

            // Create the connection
            URL url = new URL("https://www.yourserver.com");
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setSSLSocketFactory(ctx.getSocketFactory());

            // Setup the connection and connect
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Print Response Code
            System.out.println(urlConnection.getResponseCode());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}