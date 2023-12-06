import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;

public class certificate_validation_1_Req4 {
    public static void main(String[] args) throws Exception {
        // Specify the keystore details (keystore.jks) and password for ssl certificate validation
        String keystoreFile = "path/to/keystore.jks";
        String password = "password";

        // Load the JDK's cacerts keystore file
        FileInputStream is = new FileInputStream(keystoreFile);
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(is, password.toCharArray());

        // Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keystore);

        // Create an SSLContext that uses our TrustManager
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, tmf.getTrustManagers(), null);

        // Tell the URLConnection to use a SocketFactory from our SSLContext
        URL url = new URL("https://www.securewebsite.com");
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setSSLSocketFactory(context.getSocketFactory());

        InputStream in = urlConnection.getInputStream();
        // ... read from 'in' and close it when done
        in.close();
    }
}